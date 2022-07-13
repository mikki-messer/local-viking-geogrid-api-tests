package com.github.mikkimesser.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CreateGeogridRequestBody {
	@SerializedName("business_name")
	private String businessName;
	@SerializedName("grid_point_distance")
	private Double gridPointDistance;
	@SerializedName("local_language_enabled")
	private Boolean localLanguageEnabled;
	@SerializedName("grid_center_lng")
	private Double gridCenterLng;
	@SerializedName("grid_size")
	private Integer gridSize;
	@SerializedName("business_country")
	private String businessCountry;
	@SerializedName("search_term")
	private String searchTerm;
	@SerializedName("business_place_id")
	private String businessPlaceId;
	@SerializedName("grid_distance_measure")
	private String gridDistanceMeasure;
	@SerializedName("grid_center_lat")
	private Double gridCenterLat;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}