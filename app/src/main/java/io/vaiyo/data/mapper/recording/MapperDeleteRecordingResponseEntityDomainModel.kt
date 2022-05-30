package io.vaiyo.data.mapper.recording

import io.vaiyo.data.entity.response.*
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.response.*
import javax.inject.Inject

class MapperDeleteRecordingResponseEntityDomainModel @Inject constructor() :
    EntityMapper<DeleteRecordingResponseEntity, DeleteRecordingResponse> {

    override fun mapFromEntity(entity: DeleteRecordingResponseEntity): DeleteRecordingResponse =
        DeleteRecordingResponse(
            returnCode = entity.returnCode,
            deleted = entity.deleted,
        )

    override fun mapToEntity(domainModel: DeleteRecordingResponse): DeleteRecordingResponseEntity =
        DeleteRecordingResponseEntity(
            returnCode = domainModel.returnCode,
            deleted = domainModel.deleted,

        )
}