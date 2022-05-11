package utils;

import config.PetObject;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

//repeated assert
public interface Asserts {
    default void TestSoftAssert(Response response, int statusCode, PetObject testPet, PetObject updatedPet) {
        SoftAssert softAssertUpd = new SoftAssert();
        softAssertUpd.assertEquals(response.getStatusCode(), statusCode); //check status code of response
        softAssertUpd.assertTrue(response.getBody().asString().contains(testPet.getId())); //check response contains old ID
        softAssertUpd.assertTrue(response.getBody().asString().contains(updatedPet.getName())); //check response contains new pet name
        softAssertUpd.assertAll();
    }
}
