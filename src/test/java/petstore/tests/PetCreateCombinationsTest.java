package petstore.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.PetEndpoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import java.util.Arrays;
import java.util.Collection;

@Concurrent
@RunWith(SerenityParameterizedRunner.class)
public class PetCreateCombinationsTest {

    @Steps
    private PetEndpoint petEndpoint;

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"",  200},
                {" ",  200},
                {"chupacabra",  200},
        });
    }

    private final String petName;
    private final int statusCode;

    public PetCreateCombinationsTest(String petName, int statusCode) {
        this.petName = petName;
        this.statusCode = statusCode;
    }

    @Test
    public void petCreateNameCombinationsTest() {
        PetModel petModel = new PetModel(
                new CategoryModel(),
                petName,
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(statusCode);
    }


}
