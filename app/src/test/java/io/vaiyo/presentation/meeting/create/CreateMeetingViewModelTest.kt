package io.vaiyo.presentation.meeting.create

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.*
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.CreateRoomRequest
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.usecase.createRoom.CreateRoomResult
import io.vaiyo.domain.usecase.createRoom.CreateRoomUseCase
import io.vaiyo.domain.utils.GeneralError
import io.vaiyo.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CreateMeetingViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CreateMeetingViewModel
    private lateinit var createRoomUseCase: CreateRoomUseCase


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this).close()
        createRoomUseCase = mockk()
        viewModel = CreateMeetingViewModel(createRoomUseCase)

    }

    private lateinit var createRoomRequest: CreateRoomRequest
    private lateinit var createRoomResult: CreateRoomResult
    private lateinit var createRoomResponse: ViewState<ApiBaseResponse>

    @Test
    fun `when call viewmode with valid data livedata will be success`() = runBlockingTest {
        val observer: Observer<ViewState<ApiBaseResponse>> = givenCreateRoomInputsSuccess()
        whenCreateMeetingSuccess()
        thenViewModelSendStatesSuccess(observer)

    }

    private fun givenCreateRoomInputsSuccess(): Observer<ViewState<ApiBaseResponse>> {
        createRoomRequest = CreateRoomRequest("name", "1","1","1","1", "1")
        val apiBaseResponse = ApiBaseResponse()
        createRoomResult = CreateRoomResult.ApiCallExecuted(ViewState.Success(apiBaseResponse))
        createRoomResponse = ViewState.Success(apiBaseResponse)

        coEvery {
            createRoomUseCase.action(createRoomRequest)
        } returns flow {
            emit(CreateRoomResult.ApiCallExecuted(state = ViewState.Loading)) // 1. show loading
            emit(createRoomResult) // 2. show success state
        }

        val observer = mockk<Observer<ViewState<ApiBaseResponse>>>() {
            every { onChanged(any()) } just Runs
        }

        viewModel.createMeetingLiveData.observeForever(observer)
        return observer
    }

    private fun whenCreateMeetingSuccess() {
        viewModel.createMeeting(createRoomRequest)
    }

    private fun thenViewModelSendStatesSuccess(observer: Observer<ViewState<ApiBaseResponse>>) {
        verifySequence {
            observer.onChanged(ViewState.Loading) // verify loading
            observer.onChanged(createRoomResponse) // verify success state
        }
    }


    private lateinit var createRoomResponseFailure: ViewState<ApiBaseResponse>

    @Test
    fun `when call viewmode with valid data livedata will be failure`() = runBlockingTest {
        val observer: Observer<ViewState<ApiBaseResponse>> = givenCreateRoomInputs_failure()
        whenCreateMeeting_failure()
        thenViewModelSendStates_failure(observer)

    }

    private fun givenCreateRoomInputs_failure(): Observer<ViewState<ApiBaseResponse>> {
        createRoomRequest = CreateRoomRequest("name", "1","1","1","1", "1")
        val error = GeneralError(message = "error 1!")
        createRoomResult = CreateRoomResult.ApiCallExecuted(ViewState.Failure(error))
        createRoomResponseFailure = ViewState.Failure(error)

        coEvery {
            createRoomUseCase.action(createRoomRequest)
        } returns flow {
            emit(CreateRoomResult.ApiCallExecuted(state = ViewState.Loading)) // 1. show loading
            emit(createRoomResult) // 2. show success state
        }

        val observer = mockk<Observer<ViewState<ApiBaseResponse>>>() {
            every { onChanged(any()) } just Runs
        }

        viewModel.createMeetingLiveData.observeForever(observer)
        return observer
    }

    private fun whenCreateMeeting_failure() {
        viewModel.createMeeting(createRoomRequest)
    }

    private fun thenViewModelSendStates_failure(observer: Observer<ViewState<ApiBaseResponse>>) {
        verifySequence {
            observer.onChanged(ViewState.Loading) // verify loading
            observer.onChanged(createRoomResponseFailure) // verify success state
        }
    }

}