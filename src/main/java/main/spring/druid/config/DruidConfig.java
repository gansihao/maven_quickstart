package main.spring.druid.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class DruidConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic druidStatView = servletContext.addServlet("DruidStatView", new StatViewServlet());
        druidStatView.setInitParameter("resetEnable", "true");
        druidStatView.setInitParameter("loginUsername", "druid");
        druidStatView.setInitParameter("loginPassword", "druid");
        druidStatView.addMapping("/druid/*");

        FilterRegistration.Dynamic webStat = servletContext.addFilter("webStat", new WebStatFilter());
        webStat.addMappingForUrlPatterns(null, false, "/*");
        webStat.setInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.json,*.ico,/druid/*");

    }

    @Bean
    public StatFilter stat() {
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(10000);
        statFilter.setLogSlowSql(true);
        return statFilter;
    }



}
