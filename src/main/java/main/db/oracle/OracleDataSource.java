package main.db.oracle;

import main.db.Util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OracleDataSource {

    static DataSource ds = null;

    static {
        try {
            Properties pro = new Properties();
            pro.setProperty(Context.PROVIDER_URL, "t3://192.168.1.233:1314");
            pro.setProperty(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
            Context ctx = new InitialContext(pro);
            ds = (DataSource) ctx.lookup("jndi/ats50");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String args[]) {
        System.out.println("start");
        long start = System.currentTimeMillis();
        run(200);
        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    private static void run2(int count) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(count);
            for (int i=0;i<count;i++) {
                executor.execute(Util.getRunnable(countTime()));
            }
            executor.shutdown();
            while(!executor.isTerminated()){};
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection countTime() throws SQLException {
        long start = System.currentTimeMillis();
        Connection connection = ds.getConnection();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
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
