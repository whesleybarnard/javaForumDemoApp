package entelect.spring.example.beans.impl;

import entelect.spring.example.beans.FoodService;
import entelect.spring.example.beans.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class HumanServiceImpl implements HumanService {
    private String name;
    private String surname;
    private List<String> pets = new ArrayList<String>();

    //@Inject
    @Autowired
    private FoodService foodService;

    //@Inject
    //@Autowired
    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    public FoodService getFoodService() {
        return foodService;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<String> getPets() {
        return pets;
    }

    @Override
    public String toString() {
        return "ContactServiceImpl{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pets=" + pets +
                '}';
    }

    @PostConstruct
    public void initObject() {
        this.name = "Jack";
        this.surname = "Black";
        this.pets.add("dog");
        this.pets.add("cat");
    }

    @PreDestroy
    public void humanClose() {
        System.out.println("human got killed...");
    }
}
