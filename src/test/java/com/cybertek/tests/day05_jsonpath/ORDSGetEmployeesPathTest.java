package com.cybertek.tests.day05_jsonpath;

import com.cybertek.tests.ORDSTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSGetEmployeesPathTest extends ORDSTestBase {

    @Test
    public void getAllITProgrammersTest() {
        // IP/ords/hr/employee?q={"job_id":"IT_PROG"}
        // query parameter with HashMap
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("q", "{\"job_id\":\"IT_PROG\"}");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramMap)
                .when().get("/employees");

        System.out.println("Status code: " + response.getStatusCode());
        response.prettyPrint();

        // print first employee id, first name, last name, email
        System.out.println("first emp id = " + response.path("items[0].employee_id"));
        System.out.println("first emp firstname = " + response.path("items[0].first_name"));
        System.out.println("first emp lastname = " + response.path("items[0].last_name"));
        System.out.println("first emp email = " + response.path("items[0].email"));

        // save IT_PROGs emails into a List of String
        List<String> emails = response.path("items.email");
        System.out.println("Count = " + emails.size());
        System.out.println("All emails = " + emails);

        // save IT_PROGs phone numbers into a List of String
        List<String> phones = response.path("items.phone_number");
        System.out.println("All phones = " + phones);

        // verify that 590.423.4568 is among phone numbers
        assertTrue(phones.contains("590.423.4568"));
    }



}
