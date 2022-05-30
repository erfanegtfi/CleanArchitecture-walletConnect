package io.vaiyo.presentation.authentication.viewModel;

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.requests.RegisterRequest
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.usecase.authentication.AuthenticationFormState
import io.vaiyo.domain.usecase.authentication.RegisterFormValidateUseCase
import io.vaiyo.domain.usecase.authentication.RegisterUseCase
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class RegisterViewModel
@Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val registerFormValidateUseCase: RegisterFormValidateUseCase,
) : BaseViewModel(){


    private val _registerForm = MutableLiveData<AuthenticationFormState>()
    val registerState: LiveData<AuthenticationFormState>
        get() = _registerForm

    private val _registerLiveData = MutableLiveData<ViewState<User>>()
    val registerLiveData: LiveData<ViewState<User>>
        get() = _registerLiveData



    fun validateFormAndRegisterUser(registerRequest: RegisterRequest) {
        registerFormValidateUseCase.action(registerRequest)
            .onEach {
                _registerForm.value = it
                if (it is AuthenticationFormState.IsDataValid)
                    if (it.isValid)
                        authenticateUser(registerRequest)
            }.launchIn(viewModelScope)
    }

   private fun authenticateUser(authenticationRequest: RegisterRequest) {
        registerUseCase.action(authenticationRequest)
            .onEach { apiResult ->
                when (apiResult) {
                    is ViewState.Success -> {
                        if (apiResult.data.user != null)
                            _registerLiveData.value = ViewState.Success(data = apiResult.data.user)
                    }
                    is ViewState.Failure -> {
                        _registerLiveData.value = ViewState.Failure(error = apiResult.error)
                    }
                    is ViewState.Loading -> {
                        _registerLiveData.value = ViewState.Loading
                    }
                }
            }.launchIn(viewModelScope)
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}