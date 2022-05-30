package io.vaiyo.domain.usecase.createRoom

import io.vaiyo.domain.abstraction.UseCase
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.CreateRoomRequest
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val repository: RoomRepository
) : UseCase<CreateRoomRequest, Flow<ViewState<ApiBaseResponse>>> {
    override fun action(param: CreateRoomRequest): Flow<ViewState<ApiBaseResponse>> {
        return repository.createRoom(param).map {
            when (it) {
                is ApiCallState.Success -> ViewState.Success(it.data)
                is ApiCallState.Failure -> ViewState.Failure(it.error)
            }
        }
    }
}

//class CreateRoomUseCase @Inject constructor(
//    private val repository: RoomRepository
//) : UseCase<CreateRoomRequest, Flow<CreateRoomResult>> {
//    override fun action(param: CreateRoomRequest): Flow<CreateRoomResult> {
//
//        return flow {
//            var formState: CreateMeetingFormState = CreateMeetingFormState.IsDataValid(true)
//            if (param.name.isBlank()) {
//                formState = CreateMeetingFormState.MeetingNameError(R.string.error_empty_input)
//                emit(CreateRoomResult.FormValidation(formState))
//            }
//
//            if (formState is CreateMeetingFormState.IsDataValid) {
//                emit(CreateRoomResult.FormValidation(formState))
//                if (formState.isValid) {
//                    emit(CreateRoomResult.ApiCallExecuted(state = ViewState.Loading))
//                    emitAll(createRoomApiCall(param))
//                }
//            }
//        }
//    }
//
//    private fun createRoomApiCall(param: CreateRoomRequest): Flow<CreateRoomResult.ApiCallExecuted> {
//        return repository.createRoom(param).map {
//            when (it) {
//                is ApiCallState.Success -> CreateRoomResult.ApiCallExecuted(state = ViewState.Success(it.data))
//                is ApiCallState.Failure -> CreateRoomResult.ApiCallExecuted(state = ViewState.Failure(it.error))
//            }
//        }
//    }
//}
//
//
//sealed class CreateRoomResult {
//    data class ApiCallExecuted(val state: ViewState<ApiBaseResponse>) : CreateRoomResult()
//    data class FormValidation(val state: CreateMeetingFormState) : CreateRoomResult()
//}