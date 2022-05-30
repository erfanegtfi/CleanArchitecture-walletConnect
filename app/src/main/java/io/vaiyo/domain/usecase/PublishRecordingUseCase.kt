package io.vaiyo.domain.usecase

import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.PublishRecordingRequest
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RecordingRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PublishRecordingUseCase @Inject constructor(
    private val repository: RecordingRepository
): UseCase<PublishRecordingRequest, Flow<ViewState<ApiBaseResponse>>> {
    override fun action(param: PublishRecordingRequest): Flow<ViewState<ApiBaseResponse>> = repository.publishRecordings(param).map {
        when (it) {
            is ApiCallState.Success -> ViewState.Success(it.data)
            is ApiCallState.Failure -> ViewState.Failure(it.error)
        }
    }
}