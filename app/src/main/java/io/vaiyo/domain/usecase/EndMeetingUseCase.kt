package io.vaiyo.domain.usecase

import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.requests.EndMeetingRequest
import io.vaiyo.domain.model.response.EndMeetingResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.MeetingRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EndMeetingUseCase @Inject constructor(
    private val repository: MeetingRepository
) : UseCase<EndMeetingRequest, Flow<ViewState<EndMeetingResponse>>> {
    override fun action(param: EndMeetingRequest): Flow<ViewState<EndMeetingResponse>> = repository.end(param).map {
        when (it) {
            is ApiCallState.Success -> ViewState.Success(it.data)
            is ApiCallState.Failure -> ViewState.Failure(it.error)
        }
    }
}