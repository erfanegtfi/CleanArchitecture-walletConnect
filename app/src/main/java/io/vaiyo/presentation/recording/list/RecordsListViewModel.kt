package io.vaiyo.presentation.recording.list;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.data.dataSource.local.preferences.SessionManagerImp
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.DeleteRecordingRequest
import io.vaiyo.domain.model.response.Recording
import io.vaiyo.domain.usecase.DeleteRecordingUseCase
import io.vaiyo.domain.usecase.RecordingListUseCase
import io.vaiyo.domain.utils.GeneralError
import io.vaiyo.presentation.base.BaseViewModel
import io.vaiyo.presentation.view.loading.LoadState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class RecordsListViewModel
@Inject constructor(
    private val recordingListUseCase: RecordingListUseCase,
    private val deleteRecordingUseCase: DeleteRecordingUseCase,
) : BaseViewModel() {


    private val _recordingListLiveData = MutableLiveData<ViewState<List<Recording>>>()
    val recordingListLiveData: LiveData<ViewState<List<Recording>>>
        get() = _recordingListLiveData

    private val _removeRecordingLiveData = MutableLiveData<ViewState<ApiBaseResponse>>()
    val removeRecordingLiveData: LiveData<ViewState<ApiBaseResponse>>
        get() = _removeRecordingLiveData

    fun getRecordingList(meetingID: String) {
        recordingListUseCase.action(meetingID)
            .onEach {
                when (it) {
                    is ViewState.Success -> {
                        loadingState.set(LoadState.Loaded)
                        if (it.data.recordings?.recordings != null)
                            _recordingListLiveData.value = ViewState.Success(it.data.recordings.recordings)
                        else
                            _recordingListLiveData.value = ViewState.Failure(GeneralError())
                    }
                    is ViewState.Failure -> {
                        loadingState.set(LoadState.Error(it.error.message))
                        _recordingListLiveData.value = ViewState.Failure(it.error)
                    }
                    is ViewState.Loading -> {
                        loadingState.set(LoadState.Loading)
                        _recordingListLiveData.value = ViewState.Loading
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun deleteRecording(meetingID: String, recordingID: String) {
        deleteRecordingUseCase.action(DeleteRecordingRequest(meetingID, recordingID))
            .onEach {
                when (it) {
                    is ViewState.Success -> {
                        _removeRecordingLiveData.value = ViewState.Success(it.data)
                    }
                    is ViewState.Failure -> {
                        _removeRecordingLiveData.value = ViewState.Failure(it.error)
                    }
                    is ViewState.Loading -> {
                        _removeRecordingLiveData.value = ViewState.Loading
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}