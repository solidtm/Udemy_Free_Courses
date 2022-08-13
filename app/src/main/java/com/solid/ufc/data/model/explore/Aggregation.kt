package com.solid.ufc.data.model.explore


import com.google.gson.annotations.SerializedName

data class Aggregation(
    @SerializedName("id")
    val id: String,
    @SerializedName("options")
    val options: List<Option>,
    @SerializedName("title")
    val title: String
)