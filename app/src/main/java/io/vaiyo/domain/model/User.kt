package io.vaiyo.domain.model


data class User(
    val name: String? = null,
    val waddress: String? = null,
    val wtype: String? = null,
    val deleted: Boolean? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val roomId: Int? = null,
    val provider: String? = null,
    val uid: String? = null,
    val acceptedTerms: String? = null,
    val emailVerified: Boolean? = null,
    val language: String? = null,
    val roleId: Int? = null,
)