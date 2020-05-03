package api;

import listener.LogListener;
import models.*;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

@Listeners(LogListener.class)
public class DummyRestApiTests {

    String cookies = "PHPSESSID=e804c5f25ccfad2ff656b107a2363960; ezoadgid_133674=-1; ezoref_133674=; ezoab_133674=mod1; active_template::133674=pub_site.1588517754";
    String contentType = "text/plain";

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://dummy.restapiexample.com";
    }

    @Test(priority = 1)
    public void getAllEmployees() {
        when()
                .request("GET", "/api/v1/employees")
                .then()
                .statusCode(200)
                .assertThat()
                .log()
                .all()
                .body("status", equalTo("success"))
                .body("data[0].employee_name", equalTo("Tiger Nixon"))
                .body("data[1].employee_name", equalTo("Garrett Winters"))
                .body("data[2].employee_age", equalTo("66"))
                .body("data[3].employee_salary", equalTo("433060"));
    }

    @Test(priority = 5)
    public void getEmployeeById() {
        String id = "1";
        String employeeName = "Tiger Nixon";
        String employeeSalary = "320800";
        String employeeAge = "61";
        String profileImage = "";
        String status = "success";
        String result = "{status='" + status + "', data={id='1', employee_name='" + employeeName + "', employee_salary='" + employeeSalary + "', employee_age='" + employeeAge + "', profile_image='" + profileImage + "'}}";

        with()
                .header("Cookie", cookies)
                .log()
                .all()
                .when()
                .request("GET", "/api/v1/employee/1")
                .then()
                .statusCode(200)
                .extract()
                .as(GetResponseModel.class);
        GetResponseModel expectedGetResponse = new GetResponseModel(status, new GetResponseDataModel(id, employeeName, employeeSalary, employeeAge, profileImage));
        assertEquals(expectedGetResponse.toString(), result);
    }

    @Test
    public void postNewEmployee() {
        String employeeName = "Max";
        String employeeSalary = "1500";
        String employeeAge = "35";

        PostRequestModel postRequest = new PostRequestModel(employeeName, employeeSalary, employeeAge);
        PostResponseModel postResponse = with()
                .body(postRequest.toString())
                .log()
                .all()
                .when()
                .request("POST", "/api/v1/create")
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .as(PostResponseModel.class);
        PostResponseModel expectedPostModel = new PostResponseModel("success", new PostResponseDataModel(employeeName, employeeSalary, employeeAge));
        assertEquals(expectedPostModel, postResponse);
    }

    @Test(priority = 2)
    public void putUpdateEmployeeById() {
        String employeeName = "Alex";
        String employeeSalary = "2500";
        String employeeAge = "43";
        String id = "23";

        PutRequestModel putRequest = new PutRequestModel(employeeName, employeeSalary, employeeAge);
        PutResponseModel putResponse = with()
                .log()
                .all()
                .header("Cookie", cookies)
                //.header("Content-Type", contentType)
                .body(putRequest.toString())
                .when()
                .request("PUT", "/api/v1/update/" + id)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract()
                .as(PutResponseModel.class);
        PutResponseModel expectedPutModel = new PutResponseModel("success", new PutResponseDataModel(employeeName, employeeSalary, employeeAge));
        assertEquals(expectedPutModel, putResponse);
    }

    @Test(priority = 3)
    public void deleteEmployeeById() {
        with()
                .header("Cookie", cookies)
                .log()
                .all()
                .when()
                .request("DELETE", "/api/v1/delete/23")
                .then()
                .statusCode(200)
                .log()
                .all()
                .assertThat()
                .body("status", equalTo("success"))
                .body("message", equalTo("successfully! deleted Records"));
    }

    @Test
    public void getNegativeAllEmployees() {
        when()
                .request("GET", "/api/v1/employee")
                .then()
                .log()
                .all()
                .statusCode(404);
    }

    @Test(priority = 4)
    public void getNegativeEmployeeById() {
        when()
                .request("GET", "/api/v1/employee/24")
                .then()
                .statusCode(400)
                .log()
                .all()
                .assertThat()
                .body("status", equalTo("failed"))
                .body("message", equalTo("Oops! someting issue found to fetch record."));
    }

    @Test
    public void postNegativeNewEmployee() {
        when()
                .request("POST", "/api/v1/create/23")
                .then()
                .log()
                .all()
                .statusCode(404);
    }

    @Test
    public void putNegativeUpdateEmployee() {
        String employeeName = "Alex";
        String employeeSalary = "2500";
        String employeeAge = "43";
        String id = "12345";

        PutRequestModel putRequest = new PutRequestModel(employeeName, employeeSalary, employeeAge);
        with()
                .header("Content-Type", "text/plain")
                .log()
                .all()
                .body(putRequest.toString())
                .when()
                .request("PUT", "/api/v1/update/" + id)
                .then()
                .statusCode(200)
                .log()
                .all()
                .assertThat()
                .body("status", equalTo("success"))
                .body("data", equalTo(null));
    }

    @Test(priority = 3)
    public void deleteNegativeEmployeeById() {
        with()
                .header("Cookie", cookies)
                .log()
                .all()
                .when()
                .request("DELETE", "/api/v1/delete/244")
                .then()
                .statusCode(200)
                .log()
                .all()
                .assertThat()
                .body("status", equalTo("failed"))
                .body("message", equalTo("Error! Id must be greater than zerp"));
    }
}