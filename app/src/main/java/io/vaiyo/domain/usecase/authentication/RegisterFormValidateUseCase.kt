package io.vaiyo.domain.usecase.authentication

import io.vaiyo.R
import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.requests.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterFormValidateUseCase @Inject constructor(
) : UseCase<RegisterRequest, Flow<AuthenticationFormState>> {
    override fun action(param: RegisterRequest): Flow<AuthenticationFormState> {

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