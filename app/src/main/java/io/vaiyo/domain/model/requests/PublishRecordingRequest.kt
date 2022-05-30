package io.vaiyo.domain.model.requests

data class PublishRecordingRequest(
    val recordID: String,
    val publish: String,
)