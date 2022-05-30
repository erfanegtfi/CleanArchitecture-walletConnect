package io.vaiyo.presentation.meeting.create;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.CreateRoomRequest
import io.vaiyo.domain.usecase.createRoom.CreateRoomUseCase
import io.vaiyo.domain.usecase.createRoom.CreateMeetingFormState
import io.vaiyo.domain.usecase.createRoom.CreateRoomFormValidateUseCase
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class CreateMeetingViewModel
@Inject constructor(
    private val createRoomUseCase: CreateRoomUseCase,
    private val createRoomFormValidateUseCase: CreateRoomFormValidateUseCase,
) : BaseViewModel() {


    private val _createMeetingForm = MutableLiveData<CreateMeetingFormState>()
    val createMeetingFormState: LiveData<CreateMeetingFormState>
        get() = _createMeetingForm

    private val _createMeetingLiveData = MutableLiveData<ViewState<ApiBaseResponse>>()
    val createMeetingLiveData: LiveData<ViewState<ApiBaseResponse>>
        get() = _createMeetingLiveData


//    fun createMeeting(room: CreateRoomRequest) {
//        createRoomUseCase.action(room)
//            .onEach { createRoomResult ->
//                when (createRoomResult) {
//                    is CreateRoomResult.ApiCallExecuted -> {
//                        _createMeetingLiveData.value = createRoomResult.state
//                    }
//                    is CreateRoomResult.FormValidation -> {
//                        _createMeetingForm.value = createRoomResult.state
//                    }
//                }
//            }.launchIn(viewModelScope)
//    }

    private fun createMeeting(room: CreateRoomRequest) {
        createRoomUseCase.action(room)
            .onStart {
                _createMeetingLiveData.value = ViewState.Loading
            }
            .onEach {
                _createMeetingLiveData.value = it
            }.launchIn(viewModelScope)
    }

    fun validateFormAndCreateRoom(room: CreateRoomRequest) {
        createRoomFormValidateUseCase.action(room)
            .onEach {
                _createMeetingForm.value = it
                if (it is CreateMeetingFormState.IsDataValid)
                    if (it.isValid)
                        createMeeting(room)
            }.launchIn(viewModelScope)
    }

//    private fun createMeetingValidForm(room: CreateRoomRequest): CreateMeetingFormState {
//        var formState: CreateMeetingFormState = CreateMeetingFormState.IsDataValid(true)
//        if (room.name.isBlank()) {
//            formState = CreateMeetingFormState.MeetingNameError(R.string.error_empty_input)
//            _createMeetingForm.value = formState
//        }
//
//        return formState
//    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}