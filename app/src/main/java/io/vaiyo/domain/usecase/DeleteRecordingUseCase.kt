package io.vaiyo.domain.usecase

import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.DeleteRecordingRequest
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RecordingRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class DeleteRecordingUseCase @Inject constructor(
    private val repository: RecordingRepository
) : UseCase<DeleteRecordingRequest, Flow<ViewState<ApiBaseResponse>>> {
    override fun action(param: DeleteRecordingRequest): Flow<ViewState<ApiBaseResponse>> = repository.deleteRecordings(param)
        .map {
            when (it) {
                is ApiCallState.Success -> ViewState.Success(it.data)
                is ApiCallState.Failure -> ViewState.Failure(it.error)
            }
        }.onStart {
            emit(ViewState.Loading)
        }
}