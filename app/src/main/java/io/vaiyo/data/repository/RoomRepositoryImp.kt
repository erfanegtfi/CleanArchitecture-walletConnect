package io.vaiyo.data.repository

import io.vaiyo.data.dataSource.remote.api.RoomApi
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.data.dataSource.remote.util.getResult
import io.vaiyo.data.mapper.room.MapperRoomListResponseEntityDomainModel
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.CreateRoomRequest
import io.vaiyo.domain.model.response.RoomListResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomRepositoryImp @Inject constructor(
    private val roomApi: RoomApi,
    private val mapper: MapperRoomListResponseEntityDomainModel
) : RoomRepository {
    override fun createRoom(createRoomRequest: CreateRoomRequest): Flow<ApiCallState<ApiBaseResponse>> = flow {
        when (val response = getResult {
            roomApi.createRoom(createRoomRequest)
        }) {
            is ApiCallState.Success -> emit(ApiCallState.Success(response.data))
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }

    override fun roomList(): Flow<ApiCallState<RoomListResponse>> = flow {
        when (val response = getResult {
            roomApi.list()
        }) {
            is ApiCallState.Success -> emit(ApiCallState.Success(mapper.mapFromEntity(response.data)))
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }

    override fun deleteRoom(): Flow<ApiCallState<ApiBaseResponse>> = flow {
        when (val response = getResult {
            roomApi.deleteRoom()
        }) {
            is ApiCallState.Success -> emit(ApiCallState.Success(response.data))
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }
}