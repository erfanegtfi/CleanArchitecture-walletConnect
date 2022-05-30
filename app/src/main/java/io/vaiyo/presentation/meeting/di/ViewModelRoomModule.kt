package io.vaiyo.presentation.meeting.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vaiyo.data.dataSource.di.ViewModelKey
import io.vaiyo.presentation.meeting.create.CreateMeetingViewModel
import io.vaiyo.presentation.meeting.join.JoinRoomViewModel
import io.vaiyo.presentation.meeting.list.RoomListMenuViewModel
import io.vaiyo.presentation.meeting.list.RoomListViewModel
import io.vaiyo.presentation.recording.list.RecordsListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Suppress("unused")
@Module
//internal
abstract class ViewModelRoomModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(CreateMeetingViewModel::class)
    abstract fun bindCreateMeetingViewModel(createViewModel: CreateMeetingViewModel): ViewModel

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(JoinRoomViewModel::class)
    abstract fun bindJoinRoomViewModel(createViewModel: JoinRoomViewModel): ViewModel

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(RoomListViewModel::class)
    abstract fun bindRoomListViewModel(roomListViewModel: RoomListViewModel): ViewModel

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(RoomListMenuViewModel::class)
    abstract fun bindRoomListMenuViewModel(roomListViewModel: RoomListMenuViewModel): ViewModel

}