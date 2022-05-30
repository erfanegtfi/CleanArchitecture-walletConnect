package io.vaiyo.presentation.meeting.list;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.data.dataSource.local.preferences.SessionManagerImp
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.usecase.DeleteRoomUseCase
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class RoomListMenuViewModel
@Inject constructor(
    private val deleteRoomUseCase: DeleteRoomUseCase,
    private val appPreferencesHelper: SessionManagerImp
) : BaseViewModel() {


    private val _removeRoomLiveData = MutableLiveData<ViewState<ApiBaseResponse>>()
    val removeRoomLiveData: LiveData<ViewState<ApiBaseResponse>>
        get() = _removeRoomLiveData


    fun deleteRoom() {
        deleteRoomUseCase.action(Any())
            .onStart {
                _removeRoomLiveData.value = ViewState.Loading
            }.onEach {
                when (it) {
                    is ViewState.Success -> {
                        _removeRoomLiveData.value = ViewState.Success(it.data)
                    }
                    is ViewState.Failure -> {
                        _removeRoomLiveData.value = ViewState.Failure(it.error)
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}