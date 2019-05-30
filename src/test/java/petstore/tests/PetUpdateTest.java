package petstore.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import petstore.endpoints.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

public class PetUpdateTest {
    private PetEndpoint petEndpoint = new PetEndpoint();
    private PetModel petModel;

    @Before
    public void preCondition() {
        petModel = new PetModel(
                205,
                new CategoryModel(),
                "Zombie",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");
        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }

    @After
    public void postCondition() {
        petEndpoint
                .deletePet(petModel.getId())
                .statusCode(200);
    }

    @Test
    public void updatePetTest() {
        petModel.setName("tiger");
        petModel.setStatus("SOLD");

        petEndpoint
                .updatePet(petModel)
                .statusCode(200);

        petEndpoint
                .getPetById(petModel.getId())
                .statusCode(200);
    }
}
