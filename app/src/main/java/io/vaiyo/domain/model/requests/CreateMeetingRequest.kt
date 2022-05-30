//package io.vaiyo.domain.model.requests
//
//import com.google.gson.annotations.SerializedName
//import io.vaiyo.domain.enums.GuestPolicy
//import io.vaiyo.domain.enums.MeetingLayout
//import io.vaiyo.presentation.base.adapter.ListItem
//
//@Deprecated("use CreateRoomRequest")
//data class CreateMeetingRequest(
//    @SerializedName("meetingID")
//    val meetingID: String,
//    @SerializedName("name")
//    val name: String, // room name
//    @SerializedName("moderatorPW")
//    val moderatorPW: String? = null,
//    @SerializedName("allowStartStopRecording")
//    val allowStartStopRecording: Boolean? = null,
//    @SerializedName("attendeePW")
//    val attendeePW: Boolean? = null,
//    @SerializedName("autoStartRecording")
//    val autoStartRecording: Boolean? = null,
//    @SerializedName("record")
//    val record: Boolean? = null,
//    @SerializedName("dialNumber")
//    val dialNumber: String? = null,
//    @SerializedName("voiceBridge")
//    val voiceBridge: Int? = null,
//    @SerializedName("maxParticipants")
//    val maxParticipants: Int? = null,
//    @SerializedName("fullName")
//    val fullName: String? = null, // user name
//    @SerializedName("logoutURL")
//    val logoutURL: String? = null,
//    @SerializedName("logo")
//    val logo: String? = null,
//    @SerializedName("bannerText")
//    val bannerText: String? = null,
//    @SerializedName("bannerColor")
//    val bannerColor: String? = null,
//    @SerializedName("parentMeetingID")
//    val parentMeetingID: String? = null,
//    @SerializedName("copyright")
//    val copyright: String? = null,
//    @SerializedName("moderatorOnlyMessage")
//    val moderatorOnlyMessage: String? = null,
//    @SerializedName("duration")
//    val duration: Boolean? = null,
//    @SerializedName("muteOnStart")
//    val muteOnStart: Boolean? = null,
//    @SerializedName("allowModsToUnmuteUsers")
//    val allowModsToUnmuteUsers: Boolean? = null,
//    @SerializedName("lockSettingsDisableMic")
//    val lockSettingsDisableMic: Boolean? = null,
//    @SerializedName("lockSettingsDisablePrivateChat")
//    val lockSettingsDisablePrivateChat: Boolean? = null,
//    @SerializedName("lockSettingsDisablePublicChat")
//    val lockSettingsDisablePublicChat: Boolean? = null,
//    @SerializedName("lockSettingsDisableNote")
//    val lockSettingsDisableNote: Boolean? = null,
//    @SerializedName("lockSettingsLockedLayout")
//    val lockSettingsLockedLayout: Boolean? = null,
//    @SerializedName("lockSettingsLockOnJoin")
//    val lockSettingsLockOnJoin: Boolean? = null,
//    @SerializedName("lockSettingsDisableCam")
//    val lockSettingsDisableCam: Boolean? = null,
//    @SerializedName("webcamsOnlyForModerator")
//    val webcamsOnlyForModerator: Boolean? = null,
//    @SerializedName("lockSettingsLockOnJoinConfigurable")
//    val lockSettingsLockOnJoinConfigurable: Boolean? = null,
//    @SerializedName("isBreakout")
//    val isBreakout: Boolean? = null,
//    @SerializedName("guestPolicy")
//    val guestPolicy: GuestPolicy? = null,
//    @SerializedName("meetingKeepEvents")
//    val meetingKeepEvents: Boolean? = null,
//    @SerializedName("endWhenNoModerator")
//    val endWhenNoModerator: Boolean? = null,
//    @SerializedName("endWhenNoModeratorDelayInMinutes")
//    val endWhenNoModeratorDelayInMinutes: Int? = null,
//    @SerializedName("sequence")
//    val sequence: Int? = null,
//    @SerializedName("meetingLayout")
//    val meetingLayout: MeetingLayout? = null,
//    @SerializedName("learningDashboardEnabled")
//    val learningDashboardEnabled: Boolean? = null,
//    @SerializedName("freeJoin")
//    val freeJoin: Boolean? = null,
//    @SerializedName("breakoutRoomsEnabled")
//    val breakoutRoomsEnabled: Boolean? = null,
//    @SerializedName("breakoutRoomsPrivateChatEnabled")
//    val breakoutRoomsPrivateChatEnabled: Boolean? = null,
//    @SerializedName("breakoutRoomsRecord")
//    val breakoutRoomsRecord: Boolean? = null,
//    @SerializedName("learningDashboardCleanupDelayInMinutes")
//    val learningDashboardCleanupDelayInMinutes: Boolean? = null,
//) : ListItem {
//    override val listID: String?
//        get() = "1"
//}