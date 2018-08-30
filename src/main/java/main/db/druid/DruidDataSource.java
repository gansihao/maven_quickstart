package main.db.druid;

import main.db.Util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DruidDataSource {

    static com.alibaba.druid.pool.DruidDataSource dds;

    static {
        dds = new com.alibaba.druid.pool.DruidDataSource();
        dds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dds.setUrl("jdbc:oracle:thin:@192.168.1.233:1521:orcl");
        dds.setUsername("ats50");
        dds.setPassword("ats50");
        dds.setMinIdle(20);
        dds.setMaxActive(200);
        dds.setMaxWait(60000);
        dds.setTimeBetweenEvictionRunsMillis(60000);
        dds.setMinEvictableIdleTimeMillis(300000);
//        dds.setFilters(filter);
    }

    public static void main(String[] args) {
        System.out.println("start");
        long start = System.currentTimeMillis();
        run(200);
        long end = System.currentTimeMillis();
    }

    private static Connection countTime() throws SQLException {
        Connection connection = dds.getConnection();
        return connection;
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
}
