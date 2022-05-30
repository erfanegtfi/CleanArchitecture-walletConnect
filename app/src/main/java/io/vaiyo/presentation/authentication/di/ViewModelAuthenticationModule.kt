package io.vaiyo.presentation.authentication.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.vaiyo.data.dataSource.di.ViewModelKey
import io.vaiyo.presentation.authentication.WalletConnect
import io.vaiyo.presentation.authentication.viewModel.LoginViewModel
import io.vaiyo.presentation.authentication.viewModel.RegisterViewModel
import io.vaiyo.presentation.authentication.viewModel.WalletConnectViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Suppress("unused")
@Module
//internal
abstract class ViewModelAuthenticationModule {

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @ExperimentalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(WalletConnectViewModel::class)
    abstract fun bindWalletConnectViewModel(walletConnect: WalletConnectViewModel): ViewModel

}