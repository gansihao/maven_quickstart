package main.spring.druid.service;

import main.db.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DruidService {

    @Autowired
    DataSource dataSource;

    public void exe() {
        System.out.println("start");
        long start = System.currentTimeMillis();
        run(10);
        long end = System.currentTimeMillis();
    }

    private Connection countTime() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    private void run(int count) {
        try {
            for (int i=0;i<count;i++) {
                Util.exeAsyn(countTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
