package io.vaiyo.presentation.meeting.join


sealed class JoinRoomFormState {
    data class IsDataValid(val isValid: Boolean) : JoinRoomFormState()
    data class UserNameError(val error: Int) : JoinRoomFormState()

}