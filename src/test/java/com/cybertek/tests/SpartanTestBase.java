package com.cybertek.tests;

import com.cybertek.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class SpartanTestBase {

    @BeforeAll
    public static void setUp(){
        System.out.println("Setup method: assigning value to the baseURI");
        baseURI = ConfigurationReader.getProperty("spartan.url");
    }
}
