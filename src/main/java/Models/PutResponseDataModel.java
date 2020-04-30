package Models;

import java.util.Objects;

public class PutResponseDataModel {
    String employee_name;
    String employee_salary;
    String employee_age;
    String id;

    public PutResponseDataModel(String employee_name, String employee_salary, String employee_age) {
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
    }

    public static boolean equals(PutResponseDataModel data, PutResponseDataModel data1) {
        return Objects.equals(data.employee_name, data1.employee_name) &&
                Objects.equals(data.employee_salary, data1.employee_salary) &&
                Objects.equals(data.employee_age, data1.employee_age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_name, employee_salary, employee_age);
    }
}
