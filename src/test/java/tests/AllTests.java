package tests;

import config.PetObject;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import steps.Steps;
import utils.Asserts;

public class AllTests extends MainTest  implements Asserts {


    Logger log = Logger.getLogger(AllTests.class);
    Steps steps = new Steps();

    @BeforeSuite
    public void setData()
    {
        id = steps.TryToGenerateID(700000, 900000, numberOfAttempts, nullPet); //(min range, max range, number of attempts of checks)

        basePet = new PetObject(id, baseName, availableStatus);

        basePetWithUpdatedName = new PetObject(id, baseName + id, availableStatus);

    }




    @Test(description = "All steps test")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test included all steps")

    public void runAllTest() {



        //Test steps
        log.info("Test 1 Add new pet");
        Response afterAddResponse = steps.AddNewPet(basePet); //add new pet step
        log.info("Assert 1. Check if added correctly");
        TestSoftAssert(afterAddResponse, 200, basePet, basePet);

        log.info("Test 2. Try to get by ID");
        PetObject petInStore = steps.GetPetFromStore(numberOfAttempts, basePet,nullPet);
        log.info("Assert 2. Check if pet Name in store is correct");
        Assert.assertEquals(petInStore.getName(), basePet.getName());//check if pet ID is correct

        log.info("Test 3. Try to get all pets by status ");
        PetObject[] arrayOfPetsByStatus = steps.GetPetsFromStoreByStatus(basePet.getStatus(), basePet, numberOfAttempts);
        log.info("Assert 3. Check if pet id in array");
        Assert.assertTrue(steps.ifIdInArray(arrayOfPetsByStatus, basePet.getId()));//check new Pet is in the Array


        log.info("Test 4. Try to update pets Name ");
        Response responseAfterPetUpdate = steps.UpdatePet(basePetWithUpdatedName);
        log.info("Assert 4. Check Update of pet is correct");
       TestSoftAssert(responseAfterPetUpdate, 200, basePet, basePetWithUpdatedName);

        log.info("Test 5. Try to delete Pet by id");
        steps.TryToDelete(numberOfAttempts, basePet);
        log.info("Assert 5. Check pet in the store after delete");
        Assert.assertEquals(steps.GetPetFromStore(numberOfAttempts,basePet,nullPet), nullPet); //check return is null
        log.info("----------------------------------------");
    }

    @Test(priority = 1, description = "All steps test")
    @Severity(SeverityLevel.MINOR)
    @Description("Failed test")
    public void failedTest()
    {

        Assert.assertEquals(nullPet,basePet);
    }

}
