package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetRequestWithJsonPathTest extends ORDSTestBase {

    /**
     * Given accept is JSON
     * When I send get request to ords/hr/employees/103
     * Then status code is 200
     * And content type header is JSON
     * AND employee_id is 103
     * AND first_name is Alexander
     * AND last_name is Hunold
     * AND salary is 9000
     */

    @DisplayName("GET ords/hr/employees/103 and jsonPath")
    @Test
    public void jsonPathSingleEmpInfoTest() {

        Response response = given().accept(ContentType.JSON)
                .and().get("/employees/103");

        System.out.println("Status code: "  + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type header: " + response.getContentType());
        assertEquals("application/json", response.contentType());

        // assign JSON response body to JsonPath object
        JsonPath json = response.jsonPath();

        // read values from JsonPath object
        int empID = json.getInt("employee_id");
        String first_name = json.getString("first_name");
        String last_name = json.getString("last_name");
        int salary = json.getInt("salary");

        System.out.println("empID = " + empID);
        System.out.println("first_name = " + first_name);
        System.out.println("last_name = " + last_name);
        System.out.println("salary = " + salary);
    }

    @DisplayName("GET ords/hr/employees and using jsonPath filters")
    @Test
    public void jsonPathFilterTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees");

        System.out.println("Status code: " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type: " + response.getContentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        JsonPath json = response.jsonPath();

        // names of employees who work in department 90
        List<String> empList = json.getList("items.findAll {it.department_id = 90}.first_name");
        System.out.println("empList = " + empList);

        // get the first name of the IT_PROG employees
        List<String> itProgsList = json.getList("items.findAll{it.job_id == 'IT_PROG'}.first_name");
        System.out.println("itProgsList: " + itProgsList);

        // emp ids of Employees whose salary is more than 5000
        List<Integer> empIds = json.getList("items.findAll{it.salary >= 5000}.employee_id");
        System.out.println("empIds: " + empIds);


    }

}
