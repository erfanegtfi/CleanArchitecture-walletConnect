package io.vaiyo.domain.model.requests

import com.google.gson.annotations.SerializedName

data class CompleteProfileRequest(
    @SerializedName("waddress")
    val waddress: String,
    @SerializedName("wtype")
    val wtype: String,
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String
)