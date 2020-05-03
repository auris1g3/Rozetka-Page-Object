package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostRequestModel {
    String name;
    String salary;
    String age;

    public PostRequestModel() {
    }

    public PostRequestModel(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"salary\":\"" + salary + "\",\"age\":\"" + age + "\"}";
    }
}
