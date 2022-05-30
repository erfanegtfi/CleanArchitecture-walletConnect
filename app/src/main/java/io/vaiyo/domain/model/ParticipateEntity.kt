package io.vaiyo.domain.model

data class Participate(
    val id: Int,
    val roomID: Int?,
    val provider: String?,
    val name: String?,
    val uid: String?,
    val username: String?,
    val lastLogin: String?,
    val lastSession: String?,
    val image: ParticipateImage?,
)

data class ParticipateImage(
    val url: String?,
)