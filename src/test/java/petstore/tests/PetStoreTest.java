package petstore.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import static petstore.endpoints.PetEndpoint.*;

@RunWith(SerenityRunner.class)
public class PetStoreTest {
    @Steps
    private PetEndpoint petEndpoint = new PetEndpoint();

    @Test
    public void getPetByIdTest() {
        int petId = 2;
        petEndpoint
                .getPetById(petId)
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {
        for (Status status : Status.values()) {
            petEndpoint
                    .getPetByStatus(status)
                    .statusCode(200);
        }
    }

    @Test
    public void createPetTest() {
        PetModel petModel = new PetModel(
                13,
                new CategoryModel(),
                "Zombie",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }
}
