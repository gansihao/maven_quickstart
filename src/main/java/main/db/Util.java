package main.db;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Util {

    public static void exe(Connection conn) {
        test(conn, 10);
    }

    private static void test2(Connection conn) {
        try {
            String sql = "select * from test1 where amount = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1, "第三方场所");
            long start = System.currentTimeMillis();
            ResultSet resultSet = statement.executeQuery();
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            while (resultSet.next()) {
                System.out.println(resultSet.getObject("hours"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void test3(Connection conn, int count) {
        try {
            String sql = "select count(1) hours from KF_TERMINAL_OPERATION t";
            Statement statement = conn.createStatement();
            long start = System.currentTimeMillis();
            ResultSet resultSet = null;
            for (int i=0; i<count; i++) {
                resultSet = statement.executeQuery(sql);
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            while (resultSet.next()) {
//                    System.out.println(resultSet.getObject("hours"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void test(Connection conn, int count) {
        try {
            String sql = "select count(1) hours from KF_TERMINAL_OPERATION t";
            PreparedStatement statement = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();
            ResultSet resultSet = null;
            for (int i=0; i<count; i++) {
                resultSet = statement.executeQuery();
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            while (resultSet.next()) {
//                    System.out.println(resultSet.getObject("hours"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exeAsyn(Connection conn) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                test(conn, 100);
            }
        }).start();
    }

    public static Runnable getRunnable(Connection conn) {
        return new Runnable() {
            @Override
            public void run() {
                test(conn, 10);
            }
        };
    }

}
