package io.vaiyo.domain.model.response

import io.vaiyo.domain.enums.ReturnCode

@Deprecated("")
data class PublishRecordingResponse(
   val returnCode: ReturnCode,
   val published: Boolean,
)