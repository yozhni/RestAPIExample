package utils;

import config.PetObject;
import io.restassured.response.Response;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;


public interface TestUtils extends ToFromJsonGenerator {

    //generate any number in range
   default String GenerateID(Integer rangeMin, Integer rangeMax) {

        Integer id = ThreadLocalRandom.current().nextInt(rangeMin, rangeMax + 1);
        return id.toString();
    }

    default PetObject GetPetByID(String id) {
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .get(id)
                .then()
                .extract()
                .response();
        return ToJava(response.getBody().asString());

    }


    default PetObject[] GetPetByStatus(String status) {
        Response response = given()
                .queryParam("status", status) //url part "?status = .."
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .get("findByStatus")
                .then()
                .extract()
                .response();
        return JsonToArray(response.getBody().asString());

    }


    default Response DeletePetByID(String id) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .delete(id)
                .then()
                .extract()
                .response();
    }

    //check id s unique
    default String TryToGenerateID(int minRange, int maxRange, int numberOfAttempts, PetObject nullPet) {
        String id = null;
        int count = 0;
        boolean inStore = true;


        while (inStore) {
            if (count == numberOfAttempts) {
                inStore = false;
            } else {
                id = GenerateID(minRange, maxRange);
                for (int i = 0; i < numberOfAttempts; i++) {
                    PetObject pet = GetPetByID(id);
                    if (pet.equals(nullPet)) {
                        count++;
                    }
                }
            }
        }
        return id;
    }





}
