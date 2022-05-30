package io.vaiyo.domain.model.requests

data class DeleteRecordingRequest(
    val meetingID: String,
    val recordID: String,
)