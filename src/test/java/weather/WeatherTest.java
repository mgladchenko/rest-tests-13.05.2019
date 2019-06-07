package weather;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class WeatherTest {

    @Test
    public void getWeatherPerCityTest() {
        // GET https://pinformer.sinoptik.ua/search.php?lang=ua&return_id=1&q=Lviv

        String cityName = "Lviv";
        RestAssured.baseURI = "https://pinformer.sinoptik.ua/";

        ValidatableResponse response = RestAssured.given()
                //.basePath("search.php")
                .param("lang", "ua")
                .param("return_id", 1)
                .param("q", cityName)
                //.log().uri()
                .get("search.php")
                .then()
               // .log().all()
                .statusCode(200);

        String responseString = response.extract().asString();
        String cityId = responseString.substring(responseString.lastIndexOf("|")+1);
        System.out.println(cityId);

        //https://pinformer.sinoptik.ua/pinformer4.php?type=js&lang=ua&id=303014487
        ValidatableResponse response1 = RestAssured.given()
                .param("type", "js")
                .param("lang", "en")
                .param("id", cityId)
                //.log().uri()
                .get("pinformer4.php")
                .then()
                .log().all()
                .statusCode(200)
                .body("any { it.key == '{pcity}' }", is(true))//Groovy path with hamcrest matchers
                .body("'{pcity}'", is(not(1)));//JSON path with hamcrest matchers
        //System.out.println(response1.extract().path("'{pcity}'"));
    }
}
