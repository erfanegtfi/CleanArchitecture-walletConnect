package io.vaiyo.data.mapper.meeting

import io.vaiyo.data.entity.response.JoinMeetingResponseEntity
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.response.JoinMeetingResponse
import javax.inject.Inject

class MapperJoinMeetingResponseEntityDomainModel @Inject constructor() :
    EntityMapper<JoinMeetingResponseEntity, JoinMeetingResponse> {

    override fun mapFromEntity(entity: JoinMeetingResponseEntity): JoinMeetingResponse =
        JoinMeetingResponse(
            returnCode = entity.returnCode,
            messageKey = entity.messageKey,
            message = entity.message,
            meetingID = entity.meetingID,
            userID = entity.userID,
            authToken = entity.authToken,
            sessionToken = entity.sessionToken,
            url = entity.url,
        )

    override fun mapToEntity(domainModel: JoinMeetingResponse): JoinMeetingResponseEntity =
        JoinMeetingResponseEntity(
            returnCode = domainModel.returnCode,
            messageKey = domainModel.messageKey,
            message = domainModel.message,
            meetingID = domainModel.meetingID,
            userID = domainModel.userID,
            authToken = domainModel.authToken,
            sessionToken = domainModel.sessionToken,
            url = domainModel.url,
        )
}