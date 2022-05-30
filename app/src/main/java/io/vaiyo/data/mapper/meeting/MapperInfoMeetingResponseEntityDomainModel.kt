package io.vaiyo.data.mapper.meeting

import io.vaiyo.data.entity.response.*
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.response.*
import javax.inject.Inject

class MapperInfoMeetingResponseEntityDomainModel @Inject constructor() :
    EntityMapper<InfoMeetingResponseEntity, InfoMeetingResponse> {

    override fun mapFromEntity(entity: InfoMeetingResponseEntity): InfoMeetingResponse =
        InfoMeetingResponse(
            returnCode = entity.returnCode,
            meetingName = entity.meetingName,
            meetingID = entity.meetingID,
            internalMeetingID = entity.internalMeetingID,
            createTime = entity.createTime,
            createDate = entity.createDate,
            voiceBridge = entity.voiceBridge,
            dialNumber = entity.dialNumber,
            attendeePW = entity.attendeePW,
            moderatorPW = entity.moderatorPW,
            running = entity.running,
            duration = entity.duration,
            hasUserJoined = entity.hasUserJoined,
            recording = entity.recording,
            isBreakout = entity.isBreakout,
            hasBeenForciblyEnded = entity.hasBeenForciblyEnded,
            startTime = entity.startTime,
            endTime = entity.endTime,
            participantCount = entity.participantCount,
            listenerCount = entity.listenerCount,
            voiceParticipantCount = entity.voiceParticipantCount,
            videoCount = entity.videoCount,
            maxUsers = entity.maxUsers,
            moderatorCount = entity.moderatorCount,
            messageKey = entity.messageKey,
            message = entity.message,
//             attendees = AttendeesResponse(
//                 attendee = entity.attendees?.attendee?.map {
//                     AttendeeResponse(
//                         userID = it.userID,
//                         fullName = it.fullName,
//                         role = it.role,
//                         isPresenter = it.isPresenter,
//                         isListeningOnly = it.isListeningOnly,
//                         hasJoinedVoice = it.hasJoinedVoice,
//                         hasVideo = it.hasVideo,
//                         clientType = it.clientType,
//                     )
//                 }
//             ),
        )

    override fun mapToEntity(domainModel: InfoMeetingResponse): InfoMeetingResponseEntity =
        InfoMeetingResponseEntity(
            returnCode = domainModel.returnCode,
            meetingName = domainModel.meetingName,
            meetingID = domainModel.meetingID,
            internalMeetingID = domainModel.internalMeetingID,
            createTime = domainModel.createTime,
            createDate = domainModel.createDate,
            voiceBridge = domainModel.voiceBridge,
            dialNumber = domainModel.dialNumber,
            attendeePW = domainModel.attendeePW,
            moderatorPW = domainModel.moderatorPW,
            running = domainModel.running,
            duration = domainModel.duration,
            hasUserJoined = domainModel.hasUserJoined,
            recording = domainModel.recording,
            isBreakout = domainModel.isBreakout,
            hasBeenForciblyEnded = domainModel.hasBeenForciblyEnded,
            startTime = domainModel.startTime,
            endTime = domainModel.endTime,
            participantCount = domainModel.participantCount,
            listenerCount = domainModel.listenerCount,
            voiceParticipantCount = domainModel.voiceParticipantCount,
            videoCount = domainModel.videoCount,
            maxUsers = domainModel.maxUsers,
            moderatorCount = domainModel.moderatorCount,
            messageKey = domainModel.messageKey,
            message = domainModel.message,
//            attendees = AttendeesResponseEntity(
//                attendee = domainModel.attendees?.attendee?.map {
//                    AttendeeResponseEntity(
//                        userID = it.userID,
//                        fullName = it.fullName,
//                        role = it.role,
//                        isPresenter = it.isPresenter,
//                        isListeningOnly = it.isListeningOnly,
//                        hasJoinedVoice = it.hasJoinedVoice,
//                        hasVideo = it.hasVideo,
//                        clientType = it.clientType,
//                    )
//                }
//            ),
        )
}