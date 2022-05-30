package io.vaiyo.data.dataSource.di.viewModel

import androidx.lifecycle.ViewModel
import io.vaiyo.data.dataSource.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vaiyo.presentation.authentication.viewModel.MViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Suppress("unused")
@Module
//internal
abstract class ViewModelModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(MViewModel::class)
    abstract fun bindMViewModel(mViewModel: MViewModel): ViewModel

}