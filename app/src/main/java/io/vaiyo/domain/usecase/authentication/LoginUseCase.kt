package io.vaiyo.domain.usecase.authentication

import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) : UseCase<LoginRequest, Flow<ViewState<AuthenticationResponse>>> {
    override fun action(param: LoginRequest): Flow<ViewState<AuthenticationResponse>> {

        return repository.login(param)
            .map {
                when (it) {
                    is ApiCallState.Success -> ViewState.Success(it.data)
                    is ApiCallState.Failure -> ViewState.Failure(it.error)
                }
            }.onStart {
                emit(ViewState.Loading)
            }
    }
}

//class LoginUseCase @Inject constructor(
//    private val repository: AuthenticationRepository
//) : UseCase<LoginRequest, Flow<LoginResult>> {
//    override fun action(param: LoginRequest): Flow<LoginResult> {
//
//        return flow {
//            var formState: AuthenticationFormState = AuthenticationFormState.IsDataValid(true)
//            if (param.email?.isBlank() == true) {
//                formState = AuthenticationFormState.InvalidEmail(R.string.error_empty_input)
//                emit(LoginResult.FormValidation(formState))
//            }
//
//            if (formState is AuthenticationFormState.IsDataValid) {
//                emit(LoginResult.FormValidation(formState))
//                if (formState.isValid) {
//                    emit(LoginResult.ApiCallExecuted(state = ViewState.Loading))
//                    emitAll(loginApiCall(param))
//                }
//            }
//        }
//    }
//
//    private fun loginApiCall(param: LoginRequest): Flow<LoginResult.ApiCallExecuted> {
//        return repository.login(param).map {
//            when (it) {
//                is ApiCallState.Success -> LoginResult.ApiCallExecuted(state = ViewState.Success(it.data))
//                is ApiCallState.Failure -> LoginResult.ApiCallExecuted(state = ViewState.Failure(it.error))
//            }
//        }
//    }
//}
//
//
//sealed class LoginResult {
//    data class ApiCallExecuted(val state: ViewState<AuthenticationResponse>) : LoginResult()
//    data class FormValidation(val state: AuthenticationFormState) : LoginResult()
//}