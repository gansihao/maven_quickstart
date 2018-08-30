package main.spring.textrecognition.config;

import main.spring.textrecognition.pojo.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("default")
@Configuration
@PropertySource("classpath:keys-word.properties")
public class WordRecognizeConfig {

    @Value("${api_key}")
    public String AK;

    @Value("${secret_key}")
    public String SK;

    @Value("${url}")
    public String url;

    @Bean
    public Keys key() {
        return new Keys(AK, SK, url);
    }
}
