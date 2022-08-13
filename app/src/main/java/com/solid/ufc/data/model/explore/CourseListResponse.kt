package com.solid.ufc.data.model.explore


import com.google.gson.annotations.SerializedName

data class CourseListResponse(
    @SerializedName("aggregations")
    val aggregations: List<Aggregation>,
    @SerializedName("boosted_language")
    val boostedLanguage: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("search_tracking_id")
    val searchTrackingId: String
)