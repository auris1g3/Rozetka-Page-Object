package models;

import java.util.Objects;

public class PostResponseDataModel {
    String name;
    String salary;
    String age;
    Integer id;

    public PostResponseDataModel(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, age);
    }

    public static boolean equals(PostResponseDataModel data, PostResponseDataModel data1) {
        return Objects.equals(data.name, data1.name) &&
                Objects.equals(data.salary, data1.salary) &&
                Objects.equals(data.age, data1.age);
    }
}

