package entelect.spring.example.config;

import entelect.spring.example.beans.FoodService;
import entelect.spring.example.beans.impl.FoodServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("entelect.spring.example.beans")
@PropertySource("classpath:myProperties.properties")
public class AppConfig {
    @Bean
    public HumanServiceBPP getHumanServiceBPP() {
        return new HumanServiceBPP();
    }

    @Bean
    public FoodService getFoodService() {
        return new FoodServiceImpl();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
