package com.excelib.systemcofing;

import com.excelib.aspect.AspectLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public AspectLog aspectLog() {
        return new AspectLog();
    }
}
