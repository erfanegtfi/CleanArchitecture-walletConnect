package io.vaiyo.presentation.meeting.di

import dagger.Component
import io.vaiyo.data.dataSource.di.component.AppComponent
import io.vaiyo.presentation.meeting.list.MeetingListActivity
import io.vaiyo.presentation.meeting.create.CreateRoomBottomSheet
import io.vaiyo.presentation.meeting.join.JoinRoomBottomSheet
import io.vaiyo.presentation.meeting.list.DialogRoomListMenu
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@RoomScope
@Component(dependencies = [AppComponent::class], modules = [ RoomModule::class, ViewModelRoomModule::class])
interface MeetingComponent {

    fun inject(roomActivity: MeetingListActivity)
    fun inject(createRoomBottomSheet: CreateRoomBottomSheet)
    fun inject(joinRoomBottomSheet: JoinRoomBottomSheet)
    fun inject(dialogRoomListMenu: DialogRoomListMenu)

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): MeetingComponent
    }
}
