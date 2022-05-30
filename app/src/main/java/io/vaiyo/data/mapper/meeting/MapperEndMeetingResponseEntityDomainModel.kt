package io.vaiyo.data.mapper.meeting

import io.vaiyo.data.entity.response.EndMeetingResponseEntity
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.response.EndMeetingResponse
import javax.inject.Inject

class MapperEndMeetingResponseEntityDomainModel @Inject constructor() :
    EntityMapper<EndMeetingResponseEntity, EndMeetingResponse> {

    override fun mapFromEntity(entity: EndMeetingResponseEntity): EndMeetingResponse =
        EndMeetingResponse(
            returnCode = entity.returnCode,
            messageKey = entity.messageKey,
            message = entity.message,
        )

    override fun mapToEntity(domainModel: EndMeetingResponse): EndMeetingResponseEntity =
        EndMeetingResponseEntity(
            returnCode = domainModel.returnCode,
            messageKey = domainModel.messageKey,
            message = domainModel.message,
        )
}