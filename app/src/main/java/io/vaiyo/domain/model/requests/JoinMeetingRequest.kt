package io.vaiyo.domain.model.requests

data class JoinMeetingRequest(
    val fullName: String,
    val meetingID: String,
    val password: String? = null,
    val createTime: String? = null,
    val userID: String? = null,
    val webVoiceConf: String? = null,
    val configToken: String? = null,
    val defaultLayout: String? = null,
    val avatarURL: String? = null,
    val redirect: String? = null,
    val clientURL: String? = null,
    val guest: String? = null,
    val role: String? = null,
    val excludeFromDashboard: String? = null,
)