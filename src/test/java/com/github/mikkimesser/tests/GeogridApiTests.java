package com.github.mikkimesser.tests;

import com.github.mikkimesser.models.CreateGeogridRequestBody;
import com.github.mikkimesser.models.FetchGeogridResponseBody;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.mikkimesser.helpers.CustomApiListener.withCustomTemplates;
import static com.github.mikkimesser.specifications.Specifications.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GeogridApiTests extends TestBase {
    @Test
    @DisplayName("find geogrid by existing id test")
    void searchGeogridByExistingIdTest() {
        step("search geogrid by id and validate the result", () -> {
            String geogridId = testDataConfig.existingGeogridId();
            String endpoint = String.format("/%s", geogridId);

            FetchGeogridResponseBody responseBody = given()
                    .spec(requestSpecification)
                    .get(endpoint)
                    .then()
                    .spec(responseSpecification200)
                    .extract().as(FetchGeogridResponseBody.class);
            step("verifying fetched geogrid data", () -> {
                assertThat(responseBody.getId(), equalTo(geogridId));
                assertThat(responseBody.getRanks().length, greaterThan(0));
            });
        });
    }

    @Test
    @DisplayName("search geogrid by non-existing id test")
    void searchGeogridByNonExistingIdTest() {
        step("search geogrid by non-existent id and check the result code", () -> {
            String endpoint = String.format("/%s", testDataConfig.nonExistingGeogridId());

            step("verify result code", () ->
                    given()
                            .spec(requestSpecification)
                            .get(endpoint)
                            .then()
                            .log().all()
                            .statusCode(404));
        });
    }

    @Test
    @DisplayName("search geogrid with non-existing authorization token test")
    void searchGeogridWithNonExistingAuthTokenTest() {
        step("search geogrid with non-existent authorization token header and check the result code", () -> {
            String geogridId = testDataConfig.existingGeogridId();
            String endpoint = String.format("/%s", geogridId);

            step("verify result code", () ->
                    given()
                            .when()
                            .header("Authorization", credentialsConfig.nonExistingAuthorizationToken())
                            .filter(withCustomTemplates())
                            .log().all()
                            .get(endpoint)
                            .then()
                            .spec(responseSpecification403));
        });
    }

    @Test
    @DisplayName("search geogrid without authorization token test")
    void searchGeogridWithoutAuthTokenTest() {
        step("search geogrid without authorization token header and check the result code", () -> {
            String geogridId = testDataConfig.existingGeogridId();
            String endpoint = String.format("/%s", geogridId);

            step("verify result code", () ->
                    given()
                            .when()
                            .filter(withCustomTemplates())
                            .log().all()
                            .get(endpoint)
                            .then()
                            .spec(responseSpecification403));
        });
    }

    @Test
    @DisplayName("get geogrids list by page number test")
    void getGeogridsListByPageNumberTest() {
        step(String.format("fetching the list of geogrids on the page number %s",
                testDataConfig.geogridListPageNumber().toString()), () -> {

            String endpoint = "/";

            FetchGeogridResponseBody[] geogridsList =
                    given()
                            .spec(requestSpecification)
                            .get(endpoint)
                            .then()
                            .spec(responseSpecification200)
                            .extract().as(FetchGeogridResponseBody[].class);
            step("check fetched list length", () ->
                    assertThat(geogridsList.length, greaterThan(0)));
        });
    }

    @Test
    @DisplayName("create geogrid test")
    void createGeogridTest() {
        step("creating new geogrid", () -> {
            String endpoint = "/";

            CreateGeogridRequestBody requestBody = new CreateGeogridRequestBody();

            step("initializing create request body", () -> {
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

            });

            step("sending create request and validating the result", () -> {
                Response createResponse = given()
                        .spec(requestSpecification)
                        .body(requestBody.toString())
                        .post(endpoint)
                        .then()
                        .spec(responseSpecification201)
                        .body("id", is(notNullValue()))
                        .extract().response();

                String geogridId = createResponse.path("id");

                String getEndpoint = String.format("/%s", geogridId);
                step("validating created geogrid fields", () -> {
                    FetchGeogridResponseBody fetchResponseBody = given()
                            .spec(requestSpecification)
                            .get(getEndpoint)
                            .then()
                            .spec(responseSpecification200)
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
                });
            });
        });
    }
}
