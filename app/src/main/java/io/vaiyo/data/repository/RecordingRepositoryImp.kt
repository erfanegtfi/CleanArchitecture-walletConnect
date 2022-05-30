package io.vaiyo.data.repository

import io.vaiyo.data.dataSource.remote.api.RecordingApi
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.data.dataSource.remote.util.getResult
import io.vaiyo.data.mapper.recording.MapperDeleteRecordingResponseEntityDomainModel
import io.vaiyo.data.mapper.recording.MapperPublishRecordingResponseEntityDomainModel
import io.vaiyo.data.mapper.recording.MapperRecordingListResponseEntityDomainModel
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.DeleteRecordingRequest
import io.vaiyo.domain.model.requests.PublishRecordingRequest
import io.vaiyo.domain.model.response.RecordingListResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.RecordingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecordingRepositoryImp @Inject constructor(
    private val recordingApi: RecordingApi,
    private val mapperDeleteRecording: MapperDeleteRecordingResponseEntityDomainModel,
    private val mapperPublishRecording: MapperPublishRecordingResponseEntityDomainModel,
    private val mapperListRecording: MapperRecordingListResponseEntityDomainModel,
) : RecordingRepository {

    override fun getAllRecordings(): Flow<ApiCallState<RecordingListResponse>> = flow{
        when (val response = getResult {
            recordingApi.getAllRecordings()
        }) {
            is ApiCallState.Success -> emit(ApiCallState.Success(data = mapperListRecording.mapFromEntity(response.data)))
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }

    override fun publishRecordings(publishRecordingRequest: PublishRecordingRequest): Flow<ApiCallState<ApiBaseResponse>> = flow {
        when (val response = getResult {
            recordingApi.publishRecordings(publishRecordingRequest.recordID, publishRecordingRequest.publish)
        }) {
            is ApiCallState.Success -> emit(ApiCallState.Success(response.data))
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }

    override fun deleteRecordings(deleteRecordingRequest: DeleteRecordingRequest): Flow<ApiCallState<ApiBaseResponse>> = flow{
        when (val response = getResult {
            recordingApi.deleteRecordings(deleteRecordingRequest.meetingID, deleteRecordingRequest.recordID)
        }) {
            is ApiCallState.Success -> emit(ApiCallState.Success(response.data))
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }

    override fun getRecordings(meetingID: String): Flow<ApiCallState<RecordingListResponse>> = flow {
        when (val response = getResult {
            recordingApi.getRecording(meetingID)
        }) {
            is ApiCallState.Success -> emit(ApiCallState.Success(data = mapperListRecording.mapFromEntity(response.data)))
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }
}