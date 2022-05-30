package io.vaiyo.presentation.meeting.di


import dagger.Module
import dagger.Provides
import io.vaiyo.data.dataSource.remote.api.MeetingApi
import io.vaiyo.data.dataSource.remote.api.RecordingApi
import io.vaiyo.data.dataSource.remote.api.RoomApi
import io.vaiyo.data.repository.MeetingRepositoryImp
import io.vaiyo.data.repository.RecordingRepositoryImp
import io.vaiyo.data.repository.RoomRepositoryImp
import io.vaiyo.domain.repository.MeetingRepository
import io.vaiyo.domain.repository.RecordingRepository
import io.vaiyo.domain.repository.RoomRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit


@Module
@ExperimentalCoroutinesApi
class RoomModule {

    @Provides
    fun provideMeetingRepository(meetingRepository: MeetingRepositoryImp): MeetingRepository {
        return meetingRepository
    }

    @Provides
    fun provideMeetingApi(retrofit: Retrofit): MeetingApi = retrofit.create(MeetingApi::class.java)


    @Provides
    fun provideRoomRepository(roomRepository: RoomRepositoryImp): RoomRepository {
        return roomRepository
    }

    @Provides
    fun provideRoomApi(retrofit: Retrofit): RoomApi = retrofit.create(RoomApi::class.java)

}