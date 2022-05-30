package io.vaiyo.domain.model.response

import io.vaiyo.domain.enums.ReturnCode

@Deprecated("")
data class DeleteRecordingResponse(
    val returnCode: ReturnCode,
    val deleted: Boolean,
)