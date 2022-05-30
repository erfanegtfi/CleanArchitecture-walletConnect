package io.vaiyo.data.mapper.room

import io.vaiyo.data.entity.ParticipateEntity
import io.vaiyo.data.entity.ParticipateImageEntity
import io.vaiyo.data.entity.RoomEntity
import io.vaiyo.data.entity.response.*
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.Participate
import io.vaiyo.domain.model.ParticipateImage
import io.vaiyo.domain.model.Room
import io.vaiyo.domain.model.response.*
import javax.inject.Inject

class MapperRoomListResponseEntityDomainModel @Inject constructor() :
    EntityMapper<RoomListResponseEntity, RoomListResponse> {

    override fun mapFromEntity(entity: RoomListResponseEntity): RoomListResponse =
        RoomListResponse(
            rooms = entity.rooms?.map {
                Room(
                    id = it.id,
                    userId = it.userId,
                    name = it.name,
                    uid = it.uid,
                    bbbId = it.bbbId,
                    sessions = it.sessions,
                    lastSession = it.lastSession,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    roomSettings = it.roomSettings,
                    moderatorPpw = it.moderatorPpw,
                    attendeePw = it.attendeePw,
                    accessCode = it.accessCode,
                    deleted = it.deleted,
                    moderatorAccessCode = it.moderatorAccessCode,
                    participates = it.participates?.map { participate ->
                        Participate(
                            id = participate.id,
                            roomID = participate.roomID,
                            provider = participate.provider,
                            name = participate.name,
                            uid = participate.uid,
                            username = participate.username,
                            lastLogin = participate.lastLogin,
                            lastSession = participate.lastSession,
                            image = ParticipateImage(participate.image?.url),
                        )
                    },
                    totalRecordings = it.totalRecordings,
                )
            }
        )

    override fun mapToEntity(domainModel: RoomListResponse): RoomListResponseEntity =
        RoomListResponseEntity(
            rooms = domainModel.rooms?.map {
                RoomEntity(
                    id = it.id,
                    userId = it.userId,
                    name = it.name,
                    uid = it.uid,
                    bbbId = it.bbbId,
                    sessions = it.sessions,
                    lastSession = it.lastSession,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    roomSettings = it.roomSettings,
                    moderatorPpw = it.moderatorPpw,
                    attendeePw = it.attendeePw,
                    accessCode = it.accessCode,
                    deleted = it.deleted,
                    moderatorAccessCode = it.moderatorAccessCode,
                    participates = it.participates?.map { participate ->
                        ParticipateEntity(
                            id = participate.id,
                            roomID = participate.roomID,
                            provider = participate.provider,
                            name = participate.name,
                            uid = participate.uid,
                            username = participate.username,
                            lastLogin = participate.lastLogin,
                            lastSession = participate.lastSession,
                            image = ParticipateImageEntity(participate.image?.url),
                        )
                    },
                    totalRecordings = it.totalRecordings,
                )
            }
        )
}