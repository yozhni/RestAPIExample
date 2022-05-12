package tests;

import config.PetObject;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public abstract class MainTest {

    public PetObject nullPet;
    public int numberOfAttempts;
    public String baseName ;
    public String availableStatus;
    public String id;
    public PetObject basePet;
    public PetObject basePetWithUpdatedName;
    public Properties testProperties;

    @BeforeSuite
    public void setUp() {

        nullPet = new PetObject(null,null,null);
        numberOfAttempts = 10;
        baseName = "PetBaseName";
        availableStatus = "available";

        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet/";
        RestAssured.useRelaxedHTTPSValidation();



    }




}
