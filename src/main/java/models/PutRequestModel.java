package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PutRequestModel {
    String name;
    String salary;
    String age;

    public PutRequestModel() {
    }

    public PutRequestModel(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"salary\":\"" + salary + "\",\"age\":\"" + age + "\"}";
    }
}
