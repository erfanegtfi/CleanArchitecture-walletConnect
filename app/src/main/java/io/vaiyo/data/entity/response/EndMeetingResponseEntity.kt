package io.vaiyo.data.entity.response

import io.vaiyo.domain.enums.ReturnCode
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "response")
data class EndMeetingResponseEntity(
    @field:Element(name = "returncode", required = false)
    @param:Element(name = "returncode", required = false)
    val returnCode: ReturnCode,
    @field:Element(name = "messageKey", required = false)
    @param:Element(name = "messageKey", required = false)
    val messageKey: String,
    @field:Element(name = "message", required = false)
    @param:Element(name = "message", required = false)
    val message: String,
)