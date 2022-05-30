package io.vaiyo.presentation.authentication.viewModel;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.usecase.authentication.AuthenticationFormState
import io.vaiyo.domain.usecase.authentication.LoginFormValidateUseCase
import io.vaiyo.domain.usecase.authentication.LoginUseCase
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class LoginViewModel
@Inject constructor(
    private val authenticationUseCase: LoginUseCase,
    private val loginFormValidateUseCase: LoginFormValidateUseCase,
) : BaseViewModel() {



    private val _loginFormState = MutableLiveData<AuthenticationFormState>()
    val loginFormState: LiveData<AuthenticationFormState>
        get() = _loginFormState

    private val _loginLiveData = MutableLiveData<ViewState<User>>()
    val loginLiveData: LiveData<ViewState<User>>
        get() = _loginLiveData


    fun validateFormAndLoginUser(loginRequest: LoginRequest) {
        loginFormValidateUseCase.action(loginRequest)
            .onEach {
                _loginFormState.value = it
                if (it is AuthenticationFormState.IsDataValid)
                    if (it.isValid)
                        loginUser(loginRequest)
            }.launchIn(viewModelScope)
    }

    private fun loginUser(loginRequest: LoginRequest) {
        authenticationUseCase.action(loginRequest)
            .onEach { viewState ->
                when (viewState) {
                    is ViewState.Success -> {
                        if (viewState.data.user != null)
                            _loginLiveData.value = ViewState.Success(data = viewState.data.user)
                    }
                    is ViewState.Failure -> {
                        _loginLiveData.value = ViewState.Failure(error = viewState.error)
                    }
                    is ViewState.Loading -> {
                        _loginLiveData.value = ViewState.Loading
                    }
                }
            }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}