package io.vaiyo.domain.usecase

import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.response.InfoMeetingResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.MeetingRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InfoMeetingUseCase @Inject constructor(
    private val repository: MeetingRepository
) : UseCase<String, Flow<ViewState<InfoMeetingResponse>>> {
    override fun action(param: String): Flow<ViewState<InfoMeetingResponse>> = repository.info(param).map {
        when (it) {
            is ApiCallState.Success -> ViewState.Success(it.data)
            is ApiCallState.Failure -> ViewState.Failure(it.error)
        }
    }
}