package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class MainTest{


    @BeforeClass

    public void setUp() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet/";
        RestAssured.useRelaxedHTTPSValidation();


    }




}
