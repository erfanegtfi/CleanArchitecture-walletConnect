package io.vaiyo.presentation.meeting.list;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.data.dataSource.local.preferences.SessionManagerImp
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.model.Room
import io.vaiyo.domain.usecase.DeleteRoomUseCase
import io.vaiyo.domain.usecase.RoomListUseCase
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class RoomListViewModel
@Inject constructor(
    private val roomListUseCase: RoomListUseCase,
    private val deleteRoomUseCase: DeleteRoomUseCase,
    private val appPreferencesHelper: SessionManagerImp
) : BaseViewModel() {


    private val _roomListLiveData = MutableLiveData<ViewState<List<Room>>>()
    val roomListLiveData: LiveData<ViewState<List<Room>>>
        get() = _roomListLiveData

    fun getRoomList() {
        roomListUseCase.action(Any())
            .onEach {
                when (it) {
                    is ViewState.Success -> {
                        if (it.data.rooms != null)
                            _roomListLiveData.value = ViewState.Success(it.data.rooms)
                    }
                    is ViewState.Failure -> {
                        _roomListLiveData.value = ViewState.Failure(it.error)

                    }
                    is ViewState.Loading -> {
                        _roomListLiveData.value = ViewState.Loading
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}