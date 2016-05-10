package entelect.spring.example;

import entelect.spring.example.beans.HumanService;
import entelect.spring.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        HumanService humanService = context.getBean(HumanService.class);

        System.out.println("FoodName : " + humanService.getFoodService().getFoodName());

        System.out.println();
        System.out.println(humanService.getPets());

        System.out.println();
        System.out.println();

        ((AnnotationConfigApplicationContext)context).close();
    }
}
