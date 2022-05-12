package steps;

import config.PetObject;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.TestUtils;

import static io.restassured.RestAssured.given;

public class Steps implements TestUtils {


    @Step("Add new pet {0}")
    public Response AddNewPet(PetObject newPet) {
        return given()
                .header("accept", "application/json")
               .header("Content-Type", "application/json")
                .body(ToJson(newPet))
                .post()
                .then()
                .extract()
                .response();
    }





    @Step("Update pet Name by Id {0}")
    public Response UpdatePet(PetObject pet) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(ToJson(pet))
                .put()
                .then()
                .extract()
                .response();
    }



    @Step("Attempting to get pet by id of {1}, but no more then {0} times")
    public PetObject GetPetFromStore(int numberOfAttempts, PetObject testPet, PetObject nullPet) {
        PetObject petInStore = new PetObject();
        for (int i = 0; i < numberOfAttempts; i++) {
            if (petInStore.equals(nullPet)) {
                petInStore = GetPetByID(testPet.getId());
            } else break;
        }
        return petInStore;
    }

    @Step("Attempting to get pets by status {0} but no more then {2} times")
    public PetObject[] GetPetsFromStoreByStatus(String status, PetObject testPet, int numberOfAttempts) {
        PetObject[] arrayOfPetsByStatus = GetPetByStatus(testPet.getStatus());
        for (int i = 0; i < numberOfAttempts; i++) {
            if (!(ifIdInArray(arrayOfPetsByStatus, testPet.getId()))) {
                arrayOfPetsByStatus = GetPetByStatus(testPet.getStatus());
            } else break;
        }
        return arrayOfPetsByStatus;
    }

    @Step("Attempting to delete pet by id {0} times. As test API is slow  - should try to delete N  times")
    public void TryToDelete(int numberOfAttempts, PetObject testPet) {
        for (int i = 0; i < numberOfAttempts; i++) { //
            DeletePetByID(testPet.getId()); //delete
        }
    }

}
