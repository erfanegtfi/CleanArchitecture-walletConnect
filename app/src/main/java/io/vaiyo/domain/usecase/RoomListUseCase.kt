package io.vaiyo.domain.usecase

import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.response.RoomListResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RoomListUseCase @Inject constructor(
    private val repository: RoomRepository
) : UseCase<Any, Flow<ViewState<RoomListResponse>>> {
    override fun action(param: Any): Flow<ViewState<RoomListResponse>> {
        return repository.roomList().map {
            when (it) {
                is ApiCallState.Success -> ViewState.Success(it.data)
                is ApiCallState.Failure -> ViewState.Failure(it.error)
            }
        }.onStart {
            emit(ViewState.Loading)
        }
    }
}