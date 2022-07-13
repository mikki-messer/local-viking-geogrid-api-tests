package com.github.mikkimesser.tests;

import com.github.mikkimesser.configuration.TestDataConfig;
import com.github.mikkimesser.models.CreateGeogridRequestBody;
import com.github.mikkimesser.models.FetchGeogridResponseBody;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GeogridApiTests extends TestBase {
    /*
    1. Модель для респонса searchGeogridByExistingIdTest() +
    2. Cпецификации
    3. Аллюр лисенер
    4. Степы
    5. Шаблоны
    6. Telegram-бот
    7. Ридми.мд
     */
    @Test
    @DisplayName("find geogrid by existing id test")
    void searchGeogridByExistingIdTest() {
        String geogridId = testDataConfig.existingGeogridId();
        String endpoint = String.format("/%s", geogridId);

        FetchGeogridResponseBody responseBody = given()
                .when()
                .header("Authorization", credentialsConfig.authorizationToken())
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(FetchGeogridResponseBody.class);

        assertThat(responseBody.getId(), equalTo(geogridId));
        assertThat(responseBody.getRanks().length, greaterThan(0));
    }

    @Test
    @DisplayName("search geogrid by non-existing id test")
    void searchGeogridByNonExistingIdTest() {
        String endpoint = String.format("/%s", testDataConfig.nonExistingGeogridId());

        given()
                .when()
                .header("Authorization", credentialsConfig.authorizationToken())
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    @DisplayName("search geogrid with non-existing authorization token test")
    void searchGeogridWithNonExistingAuthTokenTest() {
        String geogridId = testDataConfig.existingGeogridId();
        String endpoint = String.format("/%s", geogridId);

        given()
                .when()
                .header("Authorization", credentialsConfig.nonExistingAuthorizationToken())
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(403);

    }

    @Test
    @DisplayName("search geogrid without authorization token test")
    void searchGeogridWithoutAuthTokenTest() {
        String geogridId = testDataConfig.existingGeogridId();
        String endpoint = String.format("/%s", geogridId);

        given()
                .when()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(403);
    }

    @Test
    @DisplayName("get geogrids list by page number test")
    void getGeogridsListByPageNumberTest(){
        String endpoint = "/";

        FetchGeogridResponseBody[] geogridsList =
        given()
                .when()
                .header("Authorization", credentialsConfig.authorizationToken())
                .queryParam("page", testDataConfig.geogridListPageNumber().toString())
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(FetchGeogridResponseBody[].class);

        assertThat(geogridsList.length, greaterThan(0));
    }

    @Test
    @DisplayName("create geogrid test")
    void createGeogridTest(){
        String endpoint = "/";

        TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());

        CreateGeogridRequestBody requestBody = new CreateGeogridRequestBody();

        requestBody.setBusinessName(testDataConfig.businessName());
        requestBody.setBusinessPlaceId(testDataConfig.businessPlaceId());
        requestBody.setBusinessCountry(testDataConfig.businessCountry());
        requestBody.setGridCenterLat(testDataConfig.gridCenterLat());
        requestBody.setGridCenterLng(testDataConfig.gridCenterLng());
        requestBody.setGridPointDistance(testDataConfig.gridPointDistance());
        requestBody.setGridDistanceMeasure(testDataConfig.gridDistanceMeasure());
        requestBody.setGridSize(testDataConfig.gridSize());
        requestBody.setLocalLanguageEnabled(testDataConfig.localLanguageEnabled());
        requestBody.setSearchTerm(testDataConfig.searchTerm());

        Response createResponse = given()
                .when()
                .header("Authorization", credentialsConfig.authorizationToken())
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(201)
                .body("id", is(notNullValue()))
                .extract().response();

        //JsonPath jsonPathEvaluator = createResponseBody.jsonPath();
        String geogridId = createResponse.path("id");

        String getEndpoint = String.format("/%s", geogridId);

        FetchGeogridResponseBody fetchResponseBody = given()
                .when()
                .header("Authorization", credentialsConfig.authorizationToken())
                .log().all()
                .get(getEndpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(FetchGeogridResponseBody.class);

        assertThat(fetchResponseBody.getId(), notNullValue());
        assertThat(fetchResponseBody.getBusinessName(), equalTo(requestBody.getBusinessName()));
        assertThat(fetchResponseBody.getBusinessPlaceId(), equalTo(requestBody.getBusinessPlaceId()));
        assertThat(fetchResponseBody.getBusinessCountry(), equalTo(requestBody.getBusinessCountry()));
        assertThat(fetchResponseBody.getGridCenterLat(), equalTo(requestBody.getGridCenterLat()));
        assertThat(fetchResponseBody.getGridCenterLng(), equalTo(requestBody.getGridCenterLng()));
        assertThat(fetchResponseBody.getGridPointDistance(), equalTo(requestBody.getGridPointDistance()));
        assertThat(fetchResponseBody.getGridDistanceMeasure(), equalTo(requestBody.getGridDistanceMeasure()));
        assertThat(fetchResponseBody.getSearchTerm(), equalTo(requestBody.getSearchTerm()));
    }
}
