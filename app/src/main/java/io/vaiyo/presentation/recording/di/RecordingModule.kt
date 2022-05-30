package io.vaiyo.presentation.recording.di


import dagger.Module
import dagger.Provides
import io.vaiyo.data.dataSource.remote.api.RecordingApi
import io.vaiyo.data.repository.RecordingRepositoryImp
import io.vaiyo.domain.repository.RecordingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit


@Module
@ExperimentalCoroutinesApi
class RecordingModule {

    @Provides
    fun provideRoomRepository(recordingsRepository: RecordingRepositoryImp): RecordingRepository {
        return recordingsRepository
    }

    @Provides
    fun provideRecordingApi(retrofit: Retrofit): RecordingApi = retrofit.create(RecordingApi::class.java)


}