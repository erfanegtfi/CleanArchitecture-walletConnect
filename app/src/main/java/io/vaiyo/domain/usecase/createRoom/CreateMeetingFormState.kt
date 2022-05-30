package io.vaiyo.domain.usecase.createRoom

/**
 * Data validation state of the login form.
 */

sealed class CreateMeetingFormState {
    data class IsDataValid(val isValid: Boolean) : CreateMeetingFormState()
    data class MeetingNameError(val error: Int) : CreateMeetingFormState()

}