package com.solid.ufc.data.model.explore


import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("count")
    val count: Int,
    @SerializedName("key")
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("value")
    val value: String
)