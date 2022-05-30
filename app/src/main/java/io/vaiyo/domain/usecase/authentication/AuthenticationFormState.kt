package io.vaiyo.domain.usecase.authentication

/**
 * Data validation state of the login form.
 */

sealed class AuthenticationFormState {
    data class IsDataValid(val isValid: Boolean) : AuthenticationFormState()
    data class InvalidName(val error: Int) : AuthenticationFormState()
    data class InvalidEmail(val error: Int) : AuthenticationFormState()
    data class InvalidPasswordRepeat(val error: Int) : AuthenticationFormState()

}