package entelect.spring.example.config;

import entelect.spring.example.beans.FoodService;
import entelect.spring.example.beans.impl.FoodServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("entelect.spring.example.beans")
public class AppConfig {
    @Bean
    public HumanServiceBPP getHumanServiceBPP() {
        return new HumanServiceBPP();
    }

    @Bean
    public FoodService getFoodService() {
        return new FoodServiceImpl();
    }
}
