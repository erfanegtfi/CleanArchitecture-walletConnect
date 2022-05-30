package io.vaiyo.domain.usecase.authentication

import io.vaiyo.R
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoginFormValidateUseCase @Inject constructor(
) : UseCase<LoginRequest, Flow<AuthenticationFormState>> {
    override fun action(param: LoginRequest): Flow<AuthenticationFormState> {

        return flow {
            var formState: AuthenticationFormState = AuthenticationFormState.IsDataValid(true)
            if (param.email?.isBlank() == true) {
                formState = AuthenticationFormState.InvalidEmail(R.string.error_empty_input)
                emit(formState)
            }
            if (param.password?.isBlank() == true) {
                formState = AuthenticationFormState.InvalidPasswordRepeat(R.string.error_empty_input)
                emit(formState)
            }
            if (formState is AuthenticationFormState.IsDataValid) {
                emit(formState)
            }
        }
    }
}