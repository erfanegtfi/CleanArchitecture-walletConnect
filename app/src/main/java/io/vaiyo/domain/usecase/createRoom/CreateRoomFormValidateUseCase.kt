package io.vaiyo.domain.usecase.createRoom

import io.vaiyo.R
import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.CreateRoomRequest
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RoomRepository
import io.vaiyo.domain.model.viewState.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CreateRoomFormValidateUseCase @Inject constructor(
) : UseCase<CreateRoomRequest, Flow<CreateMeetingFormState>> {
    override fun action(param: CreateRoomRequest): Flow<CreateMeetingFormState> {

        return flow {
            var formState: CreateMeetingFormState = CreateMeetingFormState.IsDataValid(true)
            if (param.name.isBlank()) {
                formState = CreateMeetingFormState.MeetingNameError(R.string.error_empty_input)
                emit(formState)
            }

            if (formState is CreateMeetingFormState.IsDataValid) {
                emit(formState)
            }
        }
    }

}