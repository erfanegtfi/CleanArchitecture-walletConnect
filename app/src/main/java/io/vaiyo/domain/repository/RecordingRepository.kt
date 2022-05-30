package io.vaiyo.domain.repository

import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.*
import io.vaiyo.domain.model.response.RecordingListResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import kotlinx.coroutines.flow.Flow

interface RecordingRepository {
    fun getAllRecordings(): Flow<ApiCallState<RecordingListResponse>>
    fun publishRecordings(publishRecordingRequest: PublishRecordingRequest): Flow<ApiCallState<ApiBaseResponse>>
    fun deleteRecordings(deleteRecordingRequest: DeleteRecordingRequest): Flow<ApiCallState<ApiBaseResponse>>
    fun getRecordings(meetingID: String): Flow<ApiCallState<RecordingListResponse>>
}