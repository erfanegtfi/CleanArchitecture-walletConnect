package io.vaiyo.domain.model.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("waddress")
    val waddress: String? = null,
    @SerializedName("wtype")
    val wtype: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null
)