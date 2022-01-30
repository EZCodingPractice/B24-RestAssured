package com.cybertek.tests.day04_ords_path;

import com.cybertek.tests.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class GetSpartanUsingPath extends SpartanTestBase {

    /**
     * Given accept is JSON
     * And path param is 13
     * When user GET request to /api/spartans/{id}
     * Then status code is 200
     * And content type is JSON
     * And id value is 13
     * And name is "Jaimie"
     * And gender is "Female"
     * And phone is "7842554879"
     */
    @DisplayName("JSON with Path Test")
    @Test
    public void readJsonWithPathTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13 )
                .when().get("/api/spartans/{id}");

        System.out.println("Status code: " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type: " + response.getContentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        // stores values from JSON to variables
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        // print variables
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(13, id);
        assertEquals("Jaimie", response.path("name"));
        assertEquals("Female", response.path("gender"));
        assertEquals(7842554879L, phone);
        assertEquals("7842554879", response.path("phone").toString());
    }

    /**
     * Given accept is JSON
     * When user GET request to /api/spartans
     * Then status code is 200
     * And content type is JSON
     * And user navigate json array object
     */
    @Test
    public void readJsonArrayTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        System.out.println("Status code: " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type: " + response.getContentType());
        assertEquals("application/json", response.contentType());

        System.out.println("id = " + response.path("id"));
        System.out.println("id of first spartan = " + response.path("[0].id"));

        System.out.println("name of second person = " + response.path("name"));
        System.out.println("name of the first spartan = " + response.path("[0].name"));

        System.out.println(response.path("[0]").toString());

        // Print last person info
        System.out.println("LAST PERSON INFO --");
        System.out.println(response.path("id[-1]").toString());
        System.out.println(response.path("name[-1]").toString());
        System.out.println(response.path("gender[-1]").toString());
        System.out.println(response.path("phone[-1]").toString());

        List<String> names = response.path("name");
        for(String name : names)
            System.out.print(name + " ");
    }

}
