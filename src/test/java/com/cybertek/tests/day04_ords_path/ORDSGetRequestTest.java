package com.cybertek.tests.day04_ords_path;

import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSGetRequestTest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("ords.url");
    }

    /**
     * Given accept type is JSON
     * When user send s GET request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */

    @DisplayName("GET Request to /ords/hr/regions")
    @Test
    public void getAllRegionsTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/regions");

        System.out.println("Status code: " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content Type: " + response.getContentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Europe"));

    }

    /**
     * Given accept type is JSON
     * And Path param value is 1
     * When user sends GET request to /ords/hr/regions/{regions_id}
     * Status code should be 200
     * Content type should be "application/json"
     * And body should contain "Europe"
     */

    @DisplayName("GET Request to /ords/hr/regions/1")
    @Test
    public void getSingleRegionWithParam() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 1)
                .when().get("/regions/{id}");

        System.out.println("status code: " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content type: " + response.getContentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Europe"));
    }

    /**
     * Given accept type is JSON
     * And query param q={"region_name": "Americas"}
     * When user sends GET request to /ords/hr/regions
     * Status code should be 200
     * Content type should be "application/json"
     * And region name should be "Americas"
     * And region id should be "2"
     */
    @DisplayName("GET Request to /ords/hr/regions with query param")
    @Test
    public void getRegionNameWith() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_name\": \"Americas\"}")
                .when().get("regions");

        System.out.println("Status code: " + response.getStatusCode());
        assertEquals(200, response.statusCode());

        System.out.println("Content Type: " + response.getContentType());
        assertEquals("application/json", response.contentType());

        response.prettyPrint();
        assertTrue(response.asString().contains("Americas"));
        assertTrue(response.asString().contains("2"));

    }
}
