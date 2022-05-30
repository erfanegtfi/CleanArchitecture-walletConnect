package io.vaiyo.presentation.meeting.join;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.R
import io.vaiyo.data.dataSource.local.preferences.SessionManagerImp
import io.vaiyo.domain.model.requests.JoinMeetingRequest
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class JoinRoomViewModel
@Inject constructor(
    private val appPreferencesHelper: SessionManagerImp
) : BaseViewModel() {


    private val _createRoomForm = MutableLiveData<JoinRoomFormState>()
    val createRoomFormState: LiveData<JoinRoomFormState>
        get() = _createRoomForm

    private val _createRoomLiveData = MutableLiveData<ViewState<JoinMeetingRequest>>()
    val createRoomLiveData: LiveData<ViewState<JoinMeetingRequest>>
        get() = _createRoomLiveData


    fun joinRoom(room: JoinMeetingRequest) {
        val loginEnable = createRoomValidForm(room)
        if (loginEnable is JoinRoomFormState.IsDataValid) {
            if (loginEnable.isValid){}
        }
    }



    fun createRoomValidForm(room: JoinMeetingRequest): JoinRoomFormState {
        var formState: JoinRoomFormState = JoinRoomFormState.IsDataValid(true)
        if (room.meetingID?.isBlank() == true) {
            formState = JoinRoomFormState.UserNameError(R.string.error_empty_input)
            _createRoomForm.value = formState
        }

        return formState
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}