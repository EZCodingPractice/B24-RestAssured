package com.cybertek.tests.day05_jsonpath;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class TypeCodeInterviewTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getUserTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/users/1");

        System.out.println("Status code: " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type: " + response.getContentType());
        assertEquals("application/json; charset=utf-8", response.contentType());

        response.prettyPrint();

        // convert response body to JsonPath
        JsonPath json = response.jsonPath();

        System.out.println("Name: " + json.getString("name"));
        System.out.println("City: " + json.getString("address.city"));

        assertEquals("Leanne Graham", json.getString("name"));
        assertEquals("Gwenborough", json.getString("address.city"));

        // print company name
        String companyName = json.getString("company.name");
        System.out.println("Company name: " + companyName);
        
        // print longitude
        String longitude = json.getString("address.geo.lng");
        System.out.println("longitude: " + longitude);

        // print latitude
        String latitude = json.getString("address.geo.lat");
        System.out.println("latitude: " + latitude);
    }



}
