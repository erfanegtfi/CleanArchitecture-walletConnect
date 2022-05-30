package io.vaiyo.data.mapper.recording

import io.vaiyo.data.entity.response.*
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.response.*
import javax.inject.Inject

class MapperPublishRecordingResponseEntityDomainModel @Inject constructor() :
    EntityMapper<PublishRecordingResponseEntity, PublishRecordingResponse> {

    override fun mapFromEntity(entity: PublishRecordingResponseEntity): PublishRecordingResponse =
        PublishRecordingResponse(
            returnCode = entity.returnCode,
            published = entity.published,
        )

    override fun mapToEntity(domainModel: PublishRecordingResponse): PublishRecordingResponseEntity =
        PublishRecordingResponseEntity(
            returnCode = domainModel.returnCode,
            published = domainModel.published,

        )
}