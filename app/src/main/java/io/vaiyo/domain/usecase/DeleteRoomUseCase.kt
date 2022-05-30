package io.vaiyo.domain.usecase

import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeleteRoomUseCase @Inject constructor(
    private val repository: RoomRepository
) : UseCase<Any, Flow<ViewState<ApiBaseResponse>>> {
    override fun action(param: Any): Flow<ViewState<ApiBaseResponse>> {
        return repository.deleteRoom().map {
            when (it) {
                is ApiCallState.Success -> ViewState.Success(it.data)
                is ApiCallState.Failure -> ViewState.Failure(it.error)
            }
        }
    }
}