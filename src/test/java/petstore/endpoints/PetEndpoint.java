package petstore.endpoints;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.eclipse.jetty.util.MultiPartInputStreamParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import petstore.models.PetModel;

import java.io.File;


public class PetEndpoint {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private RequestSpecification given() {
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json");
                //.log().uri();
    }

    @Step
    public ValidatableResponse getPetById(int petId) {
        log.info("Executing: getPetById");
        return given()
                .get(Config.GET_PET_BY_ID, petId)
                .then();
                //.log().all();
    }

    public enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Step
    public ValidatableResponse getPetByStatus(Status status) {
        return given()
                .param("status", status)
                .get(Config.GET_PET_BY_STATUS)
                .then();
                //.log().all();
    }

    @Step
    public ValidatableResponse createPet(PetModel petModel) {
        return given()
                .body(petModel)
                .post(Config.CREATE_PET)
                .then();
                //.log().all();
    }

    @Step
    public ValidatableResponse deletePet(int petId) {
        return given()
                .delete(Config.DELETE_PET_BY_ID, petId)
                .then();
                //.log().all();
    }

    @Step
    public ValidatableResponse updatePet(PetModel petModel) {
        return given()
                .body(petModel)
                .put(Config.UPDATE_PET_BY_ID)
                .then();
                //.log().all();
    }

    @Step
    public ValidatableResponse uploadPetImage(int petId, File file) {
        return given()
                .contentType("multipart/form-data")
                .multiPart(new MultiPartSpecBuilder(file)
                        .fileName(file.getName())
                        .build())
                .formParam("additionalMetadata", "Pet Image")
                .post(Config.UPLOAD_PET_IMAGE, petId)
                .then();
    }


}
