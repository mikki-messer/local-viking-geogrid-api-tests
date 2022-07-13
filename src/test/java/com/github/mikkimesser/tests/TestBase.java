package com.github.mikkimesser.tests;

import com.github.mikkimesser.configuration.CredentialsConfig;
import com.github.mikkimesser.configuration.TestDataConfig;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    //public static String authToken;
    static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    static TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());

    @BeforeAll
    static void setUp()
    {
        RestAssured.baseURI = credentialsConfig.baseUrl();
        RestAssured.basePath = credentialsConfig.geogridsBasePath();
        RestAssured.config= RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout",90000)
                        .setParam("http.connection.timeout", 90000));
    }
}
