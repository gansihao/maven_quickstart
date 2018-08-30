package main.db.cusor;

import main.db.Util;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCusor {

    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");//找到oracle驱动器所在的类
            String url="jdbc:oracle:thin:@192.168.1.233:1521:orcl"; //URL地址
            String username="ats50";
            String password="ats50";
            conn= DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        run(200);
        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    private static void run2(int count) {
        ExecutorService executor = Executors.newFixedThreadPool(count);
        for (int i=0;i<count;i++) {
            try {
                executor.execute(Util.getRunnable(countTime()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        while(!executor.isTerminated()){};
    }

    private static void run(int count) {
        try {
            for (int i=0;i<count;i++) {
                Util.exeAsyn(countTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection countTime() throws SQLException {
        long start = System.currentTimeMillis();
        Connection connection = getConnection();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return connection;
    }
}
