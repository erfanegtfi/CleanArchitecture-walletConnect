package io.vaiyo.data.dataSource.remote

import io.vaiyo.BuildConfig

object NetworkEndpoint {

    private const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val MEETING_JOIN = "join"
    const val MEETING_END = "end"
    const val MEETING_INFO = "getMeetingInfo"
    const val MEETING_IS_RUNNING = "isMeetingRunning"

    const val MEETINGS = "getMeetings"

    const val RECORDINGS = "recordings"
    const val PUBLISH_RECORDINGS = "publishRecordings"
    const val GET_RECORDING = "recordings/{meetingID}"
    const val DELETE_RECORDINGS = "recordings/{meetingID}/{recordID}"

    const val ROOM_LIST = "rooms"
    const val ROOM_DELETE = "rooms"
    const val ROOM_CREATE = "rooms/create"

}