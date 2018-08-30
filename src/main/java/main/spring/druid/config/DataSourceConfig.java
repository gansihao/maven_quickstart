package main.spring.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:datasource-druid.properties")
public class DataSourceConfig {

    @Value("${druid.url}")
    String url;

    @Value("${druid.username}")
    String username;

    @Value("${druid.password}")
    String password;

    @Value("${druid.minIdle}")
    int minIdle;

    @Value("${druid.maxActive}")
    int maxActive;

    @Value("${druid.maxWait}")
    long maxWait;

    @Value("${druid.timeBetweenEvictionRunsMillis}")
    long timeBetweenEvictionRunsMillis;

    @Value("${druid.minEvictableIdleTimeMillis}")
    long minEvictableIdleTimeMillis;

    @Value("${druid.driverClassName}")
    String driverClassName;

    @Value("${druid.filters}")
    String filter;

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(driverClassName);
        dds.setUrl(url);
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setMinIdle(minIdle);
        dds.setMaxActive(maxActive);
        dds.setMaxWait(maxWait);
        dds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dds.setPoolPreparedStatements(true);
        dds.setMaxPoolPreparedStatementPerConnectionSize(20);
        dds.setFilters(filter);
        return dds;
    }
}
