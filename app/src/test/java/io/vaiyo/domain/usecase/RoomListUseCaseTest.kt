package io.vaiyo.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import io.vaiyo.data.dataSource.remote.api.RoomApi
import io.vaiyo.data.entity.RoomEntity
import io.vaiyo.data.entity.response.RoomListResponseEntity
import io.vaiyo.data.mapper.room.MapperRoomListResponseEntityDomainModel
import io.vaiyo.data.repository.RoomRepositoryImp
import io.vaiyo.domain.model.Room
import io.vaiyo.domain.model.response.RoomListResponse
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.repository.RoomRepository
import io.vaiyo.domain.utils.ErrorApp
import io.vaiyo.domain.utils.GeneralError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RoomListUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var roomRepository: RoomRepository
    private lateinit var roomListUseCase: RoomListUseCase
    private lateinit var roomApi: RoomApi

    @Before
    fun setup() {
        roomApi = mockk()

        val mapperRoomListResponseEntityDomainModel = MapperRoomListResponseEntityDomainModel()
        roomRepository = RoomRepositoryImp(roomApi, mapperRoomListResponseEntityDomainModel)
        roomListUseCase = RoomListUseCase(repository = roomRepository)

    }

    private lateinit var roomListResponseEntity: RoomListResponseEntity
    private lateinit var roomListResponse: RoomListResponse

    @Test
    fun `authentication use case for login success response`() = runBlockingTest {
        givenAuthInputsForSuccess()
        val authenticationFlow = whenInvokeUseCaseForSuccess()
        thenFlowEmitViewStateItemsForSuccess(authenticationFlow)
    }

    private fun givenAuthInputsForSuccess() {
        val roomListEntity: List<RoomEntity> = listOf()
        val roomList: List<Room> = listOf()
        roomListResponse = RoomListResponse(roomList)
        roomListResponseEntity = RoomListResponseEntity(roomListEntity)
        roomListResponseEntity.status = 200

        coEvery {
            roomApi.list()
        } returns Response.success(roomListResponseEntity)

    }

    private fun whenInvokeUseCaseForSuccess(): Flow<ViewState<RoomListResponse>> {
        return roomListUseCase.action(Any())
    }

    private suspend fun thenFlowEmitViewStateItemsForSuccess(authenticationFlow: Flow<ViewState<RoomListResponse>>) {
        authenticationFlow.test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(ViewState.Loading)
            val emission2 = awaitItem()
            Truth.assertThat(emission2).isEqualTo(ViewState.Success(data = roomListResponse))
            awaitComplete()
        }
    }

    @Test
    fun `authentication use case for login failure response`() = runBlockingTest {
        givenAuthInputsForFailure()
        val authenticationFlow = whenInvokeUseCaseForFailure()
        thenFlowEmitViewStateItemsForFailure(authenticationFlow)
    }

    private fun givenAuthInputsForFailure() {
        val jsonObject = JSONObject()
        jsonObject.put("status", 500)
        jsonObject.put("message", "error message")
        val body = jsonObject.toString().toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())

        coEvery {
            roomApi.list()
        } returns Response.error(500, body)

    }

    private fun whenInvokeUseCaseForFailure(): Flow<ViewState<RoomListResponse>> {
        return roomListUseCase.action(Any())
    }

    private suspend fun thenFlowEmitViewStateItemsForFailure(authenticationFlow: Flow<ViewState<RoomListResponse>>) {
        val generalError = GeneralError(errorBody = ErrorApp(status = 500, message = "error message"))

        authenticationFlow.test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(ViewState.Loading)
            val emission2 = awaitItem()
            Truth.assertThat(emission2).isEqualTo(ViewState.Failure(generalError))
            awaitComplete()
        }
    }
}