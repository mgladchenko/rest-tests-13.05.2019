import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class PetStoreTest {

    static {
        RestAssured.baseURI = Config.BASE_URI;
    }

    private enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Test
    public void getPetByIdTest() {
        int petId = 2;

        RestAssured.given()
                //.log().uri()
                .get(Config.GET_PET_BY_ID, petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getPetByStatus() {

        RestAssured.given()
                .log().uri()
                .param("status", Status.AVAILABLE)
                .get(Config.GET_PET_BY_STATUS)
                .then()
                .log().all()
                .statusCode(200);
    }
}
