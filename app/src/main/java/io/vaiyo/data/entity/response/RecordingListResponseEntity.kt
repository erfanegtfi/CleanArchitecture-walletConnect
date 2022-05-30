package io.vaiyo.data.entity.response

import com.google.gson.annotations.SerializedName
import io.vaiyo.domain.enums.ReturnCode
import io.vaiyo.domain.model.base.ApiBaseResponse

data class RecordingListResponseEntity(
    @SerializedName("recordings") val recordings: RecordingsResponseEntity?,
) : ApiBaseResponse()

data class RecordingsResponseEntity(
    @SerializedName("returncode") val returnCode: Boolean?,
    @SerializedName("recordings") val recordings: List<RecordingEntity>?,
)

data class RecordingEntity(
    @SerializedName("recordID") val recordID: String?,
    @SerializedName("meetingID") val meetingID: String?,
    @SerializedName("internalMeetingID") val internalMeetingID: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("isBreakout") val isBreakout: Boolean?,
    @SerializedName("published") val published: Boolean?,
    @SerializedName("state") val state: String?,
    @SerializedName("startTime") val startTime: String?,
    @SerializedName("endTime") val endTime: String?,
    @SerializedName("participants") val participants: String?,
    @SerializedName("metadata") val metadata: RecordingMetadataEntity?,
    @SerializedName("playback") val playback: RecordingPlaybackEntity?,
)

data class RecordingPlaybackEntity(
    @SerializedName("format") val format: RecordingPlaybackFormatEntity?,
)

data class RecordingPlaybackFormatEntity(
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("processingTime") val processingTime: Int?,
    @SerializedName("length") val length: Int?,
    @SerializedName("preview") val preview: RecordingFormatPreviewEntity?,
)

data class RecordingFormatPreviewEntity(
    @SerializedName("images") val images: RecordingFormatImagesEntity?,
)

data class RecordingFormatImagesEntity(
    @SerializedName("image") val images: List<RecordingFormatPreviewImageEntity>?,
)

data class RecordingFormatPreviewImageEntity(
    @SerializedName("content") val image: String?,
)

///

data class RecordingMetadataEntity(
    @SerializedName("isBreakout") val isBreakout: Boolean?,
    @SerializedName("meetingName") val meetingName: String?,
    @SerializedName("gl-listed") val gllisted: String?,
    @SerializedName("meetingId") val meetingId: String?,
)