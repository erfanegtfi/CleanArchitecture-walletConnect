package io.vaiyo.presentation.recording.di

import dagger.Component
import io.vaiyo.data.dataSource.di.component.AppComponent
import io.vaiyo.presentation.meeting.di.ViewModelRoomModule
import io.vaiyo.presentation.recording.list.RecordListActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@RecordingScope
@Component(dependencies = [AppComponent::class], modules = [ RecordingModule::class, ViewModelRecordingModule::class,])
interface RecordingComponent {

    fun inject(recordingListActivity: RecordListActivity)


    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): RecordingComponent
    }
}
