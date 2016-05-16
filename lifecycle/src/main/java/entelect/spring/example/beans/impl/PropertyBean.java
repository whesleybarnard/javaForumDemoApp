package entelect.spring.example.beans.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PropertyBean {
    @Value("${my.user.name}")
    private String userName;
    @Value("${my.user.surname}")
    private String userSurname;

    @PostConstruct
    public void printProps() {
        System.out.println("Properties...");
        System.out.println(this.userName);
        System.out.println(this.userSurname);
    }
}
