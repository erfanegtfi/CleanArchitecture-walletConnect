package io.vaiyo.domain.model.requests

data class EndMeetingRequest(
    val password: String,
    val meetingID: String,
)