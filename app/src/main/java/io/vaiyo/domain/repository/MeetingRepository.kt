package io.vaiyo.domain.repository

import io.vaiyo.domain.model.requests.EndMeetingRequest
import io.vaiyo.domain.model.requests.JoinMeetingRequest
import io.vaiyo.domain.model.response.*
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow

interface MeetingRepository {
    fun join(join: JoinMeetingRequest): Flow<ApiCallState<JoinMeetingResponse>>
    fun end(end: EndMeetingRequest): Flow<ApiCallState<EndMeetingResponse>>
    fun info(meetingID: String): Flow<ApiCallState<InfoMeetingResponse>>
    fun isRunning(meetingID: String): Flow<ApiCallState<IsMeetingRunningResponse>>
}