package com.github.mikkimesser.specifications;

import com.github.mikkimesser.configuration.CredentialsConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static com.github.mikkimesser.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Specifications {
    static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    public static RequestSpecification requestSpecification = with()
            .filter(withCustomTemplates())
            .header("Authorization", credentialsConfig.authorizationToken())
            .contentType(JSON)
            .log().all(true);

    public static ResponseSpecification responseSpecification200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification403 = new ResponseSpecBuilder()
            .expectStatusCode(403)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.ALL)
            .build();
}
