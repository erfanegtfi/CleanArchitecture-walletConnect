package io.vaiyo.data.mapper.recording

import io.vaiyo.data.entity.response.*
import io.vaiyo.data.entity.response.RecordingFormatImagesEntity
import io.vaiyo.domain.abstraction.EntityMapper
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.response.*
import javax.inject.Inject

class MapperRecordingListResponseEntityDomainModel @Inject constructor() :
    EntityMapper<RecordingListResponseEntity, RecordingListResponse> {

    override fun mapFromEntity(entity: RecordingListResponseEntity): RecordingListResponse =
        RecordingListResponse(
            recordings = Recordings(
                returnCode = entity.recordings?.returnCode,
                recordings = entity.recordings?.recordings?.map {
                    Recording(
                        recordID = it.recordID,
                        meetingID = it.meetingID,
                        internalMeetingID = it.internalMeetingID,
                        name = it.name,
                        isBreakout = it.isBreakout,
                        published = it.published,
                        state = it.state,
                        startTime = it.startTime,
                        endTime = it.endTime,
                        participants = it.participants,
                        metadata = RecordingMetadata(
                            isBreakout = it.metadata?.isBreakout,
                            meetingName = it.metadata?.meetingName,
                            gllisted = it.metadata?.gllisted,
                            meetingId = it.metadata?.meetingId,
                        ),
                        playback = RecordingPlayback(
                            format = RecordingPlaybackFormat(
                                type = it.playback?.format?.type,
                                url = it.playback?.format?.url,
                                processingTime = it.playback?.format?.processingTime,
                                length = it.playback?.format?.length,
                                preview = RecordingFormatPreview(
                                    images = RecordingFormatImages(
                                        image = it.playback?.format?.preview?.images?.images?.map { preview ->
                                            RecordingFormatPreviewImage(image = preview.image)
                                        },
                                    )
                                )
                            )

                        )
                    )
                }
            )
        )

    override fun mapToEntity(domainModel: RecordingListResponse): RecordingListResponseEntity =
        RecordingListResponseEntity(
            recordings = RecordingsResponseEntity(
                returnCode = domainModel.recordings?.returnCode,
                recordings = domainModel.recordings?.recordings?.map {
                    RecordingEntity(
                        recordID = it.recordID,
                        meetingID = it.meetingID,
                        internalMeetingID = it.internalMeetingID,
                        name = it.name,
                        isBreakout = it.isBreakout,
                        published = it.published,
                        state = it.state,
                        startTime = it.startTime,
                        endTime = it.endTime,
                        participants = it.participants,
                        metadata = RecordingMetadataEntity(
                            isBreakout = it.metadata?.isBreakout,
                            meetingName = it.metadata?.meetingName,
                            gllisted = it.metadata?.gllisted,
                            meetingId = it.metadata?.meetingId,
                        ),
                        playback = RecordingPlaybackEntity(
                            format = RecordingPlaybackFormatEntity(
                                type = it.playback?.format?.type,
                                url = it.playback?.format?.url,
                                processingTime = it.playback?.format?.processingTime,
                                length = it.playback?.format?.length,
                                preview = RecordingFormatPreviewEntity(
                                    images = RecordingFormatImagesEntity(
                                        it.playback?.format?.preview?.images?.image?.map { preview ->
                                            RecordingFormatPreviewImageEntity(
                                                image = preview.image
                                            )
                                        },
                                    )
                                )
                            )
                        )
                    )
                }
            )
        )
}