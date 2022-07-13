package com.github.mikkimesser.configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configuration/testData.properties")
public interface TestDataConfig extends Config {
    @Key("business_name")
    String businessName();

    @Key("business_place_id")
    String businessPlaceId();

    @Key("grid_center_lat")
    Double gridCenterLat();

    @Key("grid_center_lng")
    Double gridCenterLng();

    @Key("grid_size")
    Integer gridSize();

    @Key("grid_point_distance")
    Double gridPointDistance();

    @Key("grid_distance_measure")
    String gridDistanceMeasure();

    @Key("business_country")
    String businessCountry();

    @Key("search_term")
    String searchTerm();

    @Key("local_language_enabled")
    Boolean localLanguageEnabled();

    @Key("existingGeogridId")
    String existingGeogridId();

    @Key("nonExistingGeogridId")
    String nonExistingGeogridId();

    @Key("geogridListPageNumber")
    Integer geogridListPageNumber();
}
