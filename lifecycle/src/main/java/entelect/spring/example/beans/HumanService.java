package entelect.spring.example.beans;

import java.util.List;

public interface HumanService {
    String getName();

    String getSurname();

    List<String> getPets();

    FoodService getFoodService();
}
