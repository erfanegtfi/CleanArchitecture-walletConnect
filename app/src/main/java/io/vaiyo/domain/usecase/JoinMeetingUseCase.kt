package io.vaiyo.domain.usecase

import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.requests.JoinMeetingRequest
import io.vaiyo.domain.model.response.JoinMeetingResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.MeetingRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JoinMeetingUseCase @Inject constructor(
    private val repository: MeetingRepository
): UseCase<JoinMeetingRequest, Flow<ViewState<JoinMeetingResponse>>> {
    override fun action(param: JoinMeetingRequest): Flow<ViewState<JoinMeetingResponse>> = repository.join(param).map {
        when (it) {
            is ApiCallState.Success -> ViewState.Success(it.data)
            is ApiCallState.Failure -> ViewState.Failure(it.error)
        }
    }
}