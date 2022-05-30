package io.vaiyo.domain.usecase.authentication

import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.requests.RegisterRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) : UseCase<RegisterRequest, Flow<ViewState<AuthenticationResponse>>> {
    override fun action(param: RegisterRequest): Flow<ViewState<AuthenticationResponse>> {

        return repository.register(param)
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

//class RegisterUseCase @Inject constructor(
//    private val repository: AuthenticationRepository
//) : UseCase<RegisterRequest, Flow<RegisterResult>> {
//    override fun action(param: RegisterRequest): Flow<RegisterResult> {
//
//        return flow {
//            var formState: AuthenticationFormState = AuthenticationFormState.IsDataValid(true)
//            if (param.email?.isBlank() == true) {
//                formState = AuthenticationFormState.InvalidEmail(R.string.error_empty_input)
//                emit(RegisterResult.FormValidation(formState))
//            }
//
//            if (formState is AuthenticationFormState.IsDataValid) {
//                emit(RegisterResult.FormValidation(formState))
//                if (formState.isValid) {
//                    emit(RegisterResult.ApiCallExecuted(state = ViewState.Loading))
//                    emitAll(registerApiCall(param))
//                }
//            }
//        }
//    }
//
//    private fun registerApiCall(param: RegisterRequest): Flow<RegisterResult.ApiCallExecuted> {
//        return repository.register(param).map {
//            when (it) {
//                is ApiCallState.Success -> RegisterResult.ApiCallExecuted(state = ViewState.Success(it.data))
//                is ApiCallState.Failure -> RegisterResult.ApiCallExecuted(state = ViewState.Failure(it.error))
//            }
//        }
//    }
//
//}
//
//sealed class RegisterResult {
//    data class ApiCallExecuted(val state: ViewState<AuthenticationResponse>) : RegisterResult()
//    data class FormValidation(val state: AuthenticationFormState) : RegisterResult()
//}