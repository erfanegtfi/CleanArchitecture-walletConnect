package io.vaiyo.data.repository

import io.vaiyo.data.dataSource.remote.api.MeetingApi
import io.vaiyo.data.mapper.meeting.*
import io.vaiyo.domain.model.requests.EndMeetingRequest
import io.vaiyo.domain.model.requests.JoinMeetingRequest
import io.vaiyo.domain.model.response.*
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.MeetingRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MeetingRepositoryImp @Inject constructor(
    private val meetingApi: MeetingApi,
    private val endMeetingMapper: MapperEndMeetingResponseEntityDomainModel,
    private val joinMeetingMapper: MapperJoinMeetingResponseEntityDomainModel,
    private val infoMeetingMapper: MapperInfoMeetingResponseEntityDomainModel,
    private val isMeetingRunningMapper: MapperIsMeetingRunningResponseEntityDomainModel
) : MeetingRepository {


    override fun join(join: JoinMeetingRequest): Flow<ApiCallState<JoinMeetingResponse>> = flow {

    }

    override fun end(end: EndMeetingRequest): Flow<ApiCallState<EndMeetingResponse>> =
        flow {

        }

    override fun info(meetingID: String): Flow<ApiCallState<InfoMeetingResponse>> = flow {

    }

    override fun isRunning(meetingID: String): Flow<ApiCallState<IsMeetingRunningResponse>> = flow {

    }
}