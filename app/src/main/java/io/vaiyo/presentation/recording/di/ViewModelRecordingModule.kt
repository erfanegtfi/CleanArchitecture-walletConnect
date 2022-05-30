package io.vaiyo.presentation.recording.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vaiyo.data.dataSource.di.ViewModelKey
import io.vaiyo.presentation.meeting.create.CreateMeetingViewModel
import io.vaiyo.presentation.recording.list.RecordsListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Suppress("unused")
@Module
//internal
abstract class ViewModelRecordingModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(RecordsListViewModel::class)
    abstract fun bindRecordsListViewModel(recordsListViewModel: RecordsListViewModel): ViewModel
}