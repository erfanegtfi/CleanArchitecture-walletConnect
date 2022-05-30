package io.vaiyo.presentation.authentication.viewModel;

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class MViewModel
@Inject constructor(
) : BaseViewModel() {


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}