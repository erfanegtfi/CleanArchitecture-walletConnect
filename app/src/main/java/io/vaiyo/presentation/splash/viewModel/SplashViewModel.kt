package io.vaiyo.presentation.splash.viewModel;

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class SplashViewModel
@Inject constructor(
) : BaseViewModel() {


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}