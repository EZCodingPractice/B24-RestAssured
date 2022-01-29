package com.cybertek.tests.day01_intro_to_api;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReqResGetUsersTest {

    @Test
    public void getAllUsersApiTest() {
        Response response = RestAssured.get(ConfigurationReader.getProperty("reqres.users.api.url"));
        System.out.println("Status code: " + response.getStatusCode());
        response.prettyPrint();

//        System.out.println("Response: " + response.asString());
//        System.out.println("Body: " + response.getBody().asString());
//        System.out.println("Time take: " + response.getTime());
//        System.out.println("Headers: " + response.getHeaders());

        Assertions.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void getSingleUserApiTest() {
        Response response = RestAssured
                .get(ConfigurationReader.getProperty("reqres.users.api.url") + "/5");
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        Assertions.assertEquals(200, statusCode);

        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("Charles"));
    }


    @Test
    public void getSingleUserNegativeApiTest() {
        Response response = RestAssured
                .get(ConfigurationReader.getProperty("reqres.users.api.url") + "/50");
        System.out.println("Status code: " + response.getStatusCode());

        Assertions.assertEquals(404, response.getStatusCode());

        response.prettyPrint();
        Assertions.assertTrue(response.asString().contains("{\n    \n}"));
    }
}
