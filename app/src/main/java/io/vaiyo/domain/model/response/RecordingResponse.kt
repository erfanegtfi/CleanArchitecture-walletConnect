package io.vaiyo.domain.model.response

import com.google.gson.annotations.SerializedName
import io.vaiyo.data.entity.response.RecordingFormatPreviewImageEntity
import io.vaiyo.domain.enums.ReturnCode
import io.vaiyo.presentation.utils.Utils


data class RecordingListResponse(
    val recordings: Recordings?,
)

data class Recordings(
    val returnCode: Boolean?,
    val recordings: List<Recording>?,
)

data class Recording(
    val recordID: String? = null,
    val meetingID: String? = null,
    val internalMeetingID: String? = null,
    val name: String? = null,
    val isBreakout: Boolean? = null,
    val published: Boolean? = null,
    val state: String? = null,
    val startTime: String? = null,
    val endTime: String? = null,
    val participants: String? = null,
    val metadata: RecordingMetadata? = null,
    val playback: RecordingPlayback? = null,
) {

    val duration: Long
        get() {
//            if (endTime != null && startTime != null && endTime > startTime)
//                return endTime - startTime
            return 0;
        }

  val startTimeFormatted: String
        get() {
            return Utils.formatDate2(startTime)
        }

}

data class RecordingPlayback(
    val format: RecordingPlaybackFormat?,
)

data class RecordingPlaybackFormat(
    val type: String?,
    val url: String?,
    val processingTime: Int?,
    val length: Int?,
    val preview: RecordingFormatPreview?,
)

data class RecordingFormatPreview(
    val images: RecordingFormatImages?,
)

data class RecordingFormatImages(
    val image: List<RecordingFormatPreviewImage>?,
)

data class RecordingFormatPreviewImage(
    val image: String?,
)

///

data class RecordingMetadata(
    val isBreakout: Boolean?,
    val meetingName: String?,
    val gllisted: String?,
    val meetingId: String?,
)