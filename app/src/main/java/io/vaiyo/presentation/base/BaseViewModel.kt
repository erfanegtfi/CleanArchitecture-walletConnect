package io.vaiyo.presentation.base;

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.view.loading.LoadState
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject


open class BaseViewModel @Inject constructor() : ViewModel() {

    var loadingState: ObservableField<LoadState> = ObservableField(LoadState.Initial)
    val sharedApiCallEvents = MutableSharedFlow<ViewState<ApiBaseResponse>>()

    override fun onCleared() {
        Log.v("disposables", "disposables")
        super.onCleared()
    }
}
