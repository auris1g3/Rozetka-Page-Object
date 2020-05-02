package models;

public class GetResponseDataModel {
    String id;
    String employee_name;
    String employee_salary;
    String employee_age;
    String profile_image;

    public GetResponseDataModel(String id, String employee_name, String employee_salary, String employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    @Override
    public String toString() {
        return "{" + "id='" + id + '\'' + ", employee_name='" + employee_name + '\'' + ", employee_salary='" + employee_salary + '\'' + ", employee_age='" + employee_age + '\'' + ", profile_image='" + profile_image + '\'' + '}';
    }
}
