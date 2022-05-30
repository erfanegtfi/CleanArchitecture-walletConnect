package io.vaiyo.data.entity.response

import io.vaiyo.domain.enums.ReturnCode
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "response")
data class IsMeetingRunningResponseEntity(
    @field:Element(name = "returncode", required = false)
    @param:Element(name = "returncode", required = false)
    val returnCode: ReturnCode,
    @field:Element(name = "running", required = false)
    @param:Element(name = "running", required = false)
    val running: String? = null,

    //errors
    @field:Element(name = "messageKey", required = false)
    @param:Element(name = "messageKey", required = false)
    val messageKey: String? = null,
    @field:Element(name = "message", required = false)
    @param:Element(name = "message", required = false)
    val message: String? = null,
)

//@XmlRootElement(name = "response")
//data class IsMeetingRunningResponseEntity(
//    @field:XmlElement(name = "returncode")
//    @param:XmlElement(name = "returncode")
//    val returnCode: ReturnCode,
//    @field:XmlElement(name = "running")
//    @param:XmlElement(name = "running")
//    val running: String? = null,
//)