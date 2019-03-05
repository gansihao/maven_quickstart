package main.office;

import main.office.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class SXSSFTest {

    @Test
    public void testLogin() {
        int size = 500000;
        List<User> users = new ArrayList<>(size);
        User user;
        for (int i = 0; i < size; i++) {
            user = new User();
            user.setId(Integer.toUnsignedLong(i));
            user.setAge(i + 10);
            user.setName("user" + i);
            user.setRemark(System.currentTimeMillis() + "");
            user.setSex("男");
            users.add(user);
        }

        Thread t = new Thread(() -> {
            System.out.println("start");
            String[] columnName = {"用户id", "姓名", "年龄", "性别", "备注"};
            Object[][] data = new Object[size][5];
            int index = 0;
            for (User u : users) {
                data[index][0] = u.getId();
                data[index][1] = u.getName();
                data[index][2] = u.getAge();
                data[index][3] = u.getSex();
                data[index][4] = u.getRemark();
                index++;
            }
        SXSSFWorkbook xssfWorkbook = generateExcel("test", "test", columnName, data);
        try {
            xssfWorkbook.write(new FileOutputStream(new File("D:\\poi.xlsx")));
            System.out.println("end");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        );
        t.start();

        try {
            System.out.println("join start");
            System.out.println(t);
            System.out.println(Thread.currentThread());
            t.join(60000);//等待子线程结束
            System.out.println("join end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static SXSSFWorkbook generateExcel(String sheetName, String title, String[] columnName, Object[][] data) {
        SXSSFWorkbook workBook = new SXSSFWorkbook(5000);
        // 在workbook中添加一个sheet,对应Excel文件中的sheet

        // 如果没有给定sheet名，则默认使用Sheet1
        SXSSFSheet sheet;
        if (StringUtils.isNotBlank(sheetName)) {
            sheet = workBook.createSheet(sheetName);
        } else {
            sheet = workBook.createSheet();
        }

        // 构建大标题，可以没有
        SXSSFRow headRow = sheet.createRow(0);
        SXSSFCell cell = null;
        cell = headRow.createCell(0);
        cell.setCellValue(title);

        //大标题行的偏移
        int offset = 0;
        if (StringUtils.isNotBlank(title)) {
            offset = 1;
        }

        // 构建列标题，不能为空
        headRow = sheet.createRow(offset);
        for (int i = 0; i < columnName.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellValue(columnName[i]);
        }

        // 构建表体数据（二维数组），不能为空
        for (int i = 0; i < data.length; i++) {
            headRow = sheet.createRow(++offset);
            for (int j = 0; j < data[0].length; j++) {
                cell = headRow.createCell(j);
                if (data[i][j] instanceof BigDecimal)
                    cell.setCellValue(((BigDecimal) data[i][j]).doubleValue());
                else if (data[i][j] instanceof Double)
                    cell.setCellValue((Double) data[i][j]);
                else if (data[i][j] instanceof Long)
                    cell.setCellValue((Long) data[i][j]);
                else if (data[i][j] instanceof Integer)
                    cell.setCellValue((Integer) data[i][j]);
                else if (data[i][j] instanceof Boolean)
                    cell.setCellValue((Boolean) data[i][j]);
                else if (data[i][j] instanceof Date)
                    cell.setCellValue((Date) data[i][j]);
                else
                    cell.setCellValue((String) data[i][j]);
            }
        }
        return workBook;
    }

}