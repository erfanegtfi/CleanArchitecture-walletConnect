package io.vaiyo.data.entity.response

import com.google.gson.annotations.SerializedName
import io.vaiyo.data.entity.UserEntity
import io.vaiyo.domain.model.base.ApiBaseResponse

data class AuthenticationResponseEntity(
    @SerializedName("jwt") val jwt: String? = null,
    @SerializedName("user") val user: UserEntity? = null,
): ApiBaseResponse()