package io.vaiyo.data.dataSource.di.viewModel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory


@Suppress("unused")
@Module
//internal
abstract class ViewModelFactoryModule {


    @Binds
    abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}