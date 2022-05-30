package io.vaiyo.presentation.splash.di

import io.vaiyo.presentation.splash.SplashActivity
import dagger.Component
import io.vaiyo.data.dataSource.di.component.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@SplashScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelSplashModule::class, SplashModule::class])
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)

    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): SplashComponent
    }
}