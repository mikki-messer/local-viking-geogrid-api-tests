package com.github.mikkimesser.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchGeogridResponseBody {
	@JsonProperty("business_name")
	private String businessName;
	@JsonProperty("local_language_enabled")
	private Boolean localLanguageEnabled;
	@JsonProperty("image_url")
	private String imageUrl;
	@JsonProperty("grid_center_lng")
	private Double gridCenterLng;
	@JsonProperty("grid_size")
	private Integer gridSize;
	@JsonProperty("created_at")
	private String createdAt;
	private Double agr;
	@JsonProperty("geogrid_config_id")
	private String geogridConfigId;
	@JsonProperty("grid_point_distance")
	private Double gridPointDistance;
	private GeogridRank[][] ranks;
	private Double atgr;
	private Double solv;
	@JsonProperty("business_country")
	private String businessCountry;
	@JsonProperty("business_place_id")
	private String businessPlaceId;
	@JsonProperty("grid_distance_measure")
	private String gridDistanceMeasure;
	private String id;
	private String state;
	@JsonProperty("grid_center_lat")
	private Double gridCenterLat;
	@JsonProperty("search_term")
	private String searchTerm;
}