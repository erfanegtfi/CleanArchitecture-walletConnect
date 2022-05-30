package io.vaiyo.domain.usecase

import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.response.RecordingListResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RecordingRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RecordingListUseCase @Inject constructor(
    private val repository: RecordingRepository
) : UseCase<String, Flow<ViewState<RecordingListResponse>>> {
    override fun action(param: String): Flow<ViewState<RecordingListResponse>> = repository.getRecordings(param).map {
        when (it) {
            is ApiCallState.Success -> ViewState.Success(it.data)
            is ApiCallState.Failure -> ViewState.Failure(it.error)
        }
    }.onStart {
        emit(ViewState.Loading)
    }
}