package com.github.mikkimesser.configuration;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:configuration/credentials.properties"})
public interface CredentialsConfig extends Config {
    String authorizationToken();

    @Key("nonExistingAuthorizationToken")
    String nonExistingAuthorizationToken();

    String baseUrl();

    String geogridsBasePath();
}
