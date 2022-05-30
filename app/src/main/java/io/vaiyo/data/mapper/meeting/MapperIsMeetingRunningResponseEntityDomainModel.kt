package io.vaiyo.data.mapper.meeting

import io.vaiyo.data.entity.response.IsMeetingRunningResponseEntity
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.response.IsMeetingRunningResponse
import javax.inject.Inject

class MapperIsMeetingRunningResponseEntityDomainModel @Inject constructor() :
    EntityMapper<IsMeetingRunningResponseEntity, IsMeetingRunningResponse> {

    override fun mapFromEntity(entity: IsMeetingRunningResponseEntity): IsMeetingRunningResponse =
        IsMeetingRunningResponse(
            returnCode = entity.returnCode,
            running = entity.running,
        )

    override fun mapToEntity(domainModel: IsMeetingRunningResponse): IsMeetingRunningResponseEntity =
        IsMeetingRunningResponseEntity(
            returnCode = domainModel.returnCode,
            running = domainModel.running,
        )
}