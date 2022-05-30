package io.vaiyo.presentation.authentication.di

import dagger.Component
import io.vaiyo.data.dataSource.di.component.AppComponent
import io.vaiyo.presentation.authentication.AuthenticationActivity
import io.vaiyo.presentation.authentication.FragmentAuthentication
import io.vaiyo.presentation.authentication.login.FragmentLogin
import io.vaiyo.presentation.authentication.register.FragmentRegister
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AuthenticationScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelAuthenticationModule::class, AuthenticationModule::class])
interface AuthenticationComponent {

    fun inject(loginFragment: FragmentLogin)
    fun inject(fragmentRegister: FragmentRegister)
    fun inject(authenticationActivity: AuthenticationActivity)
    fun inject(fragmentAuthentication: FragmentAuthentication)


    @Component.Factory
    interface Factory {

        fun create(appComponent: AppComponent): AuthenticationComponent
    }
}