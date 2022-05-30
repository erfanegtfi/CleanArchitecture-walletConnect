package io.vaiyo.data.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("name") val name: String? = null,
    @SerializedName("waddress") val waddress: String? = null,
    @SerializedName("wtype") val wtype: String? = null,
    @SerializedName("deleted") val deleted: Boolean? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("room_id") val roomId: Int? = null,
    @SerializedName("provider") val provider: String? = null,
    @SerializedName("uid") val uid: String? = null,
    @SerializedName("accepted_terms") val acceptedTerms: String? = null,
    @SerializedName("email_verified") val emailVerified: Boolean? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("role_id") val roleId: Int? = null,
)