package io.vaiyo.presentation.splash.di

import androidx.lifecycle.ViewModel
import io.vaiyo.presentation.splash.viewModel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vaiyo.data.dataSource.di.ViewModelKey
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Suppress("unused")
@Module
abstract class ViewModelSplashModule {
    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(loginViewModel: SplashViewModel): ViewModel


}