package io.vaiyo.presentation.authentication.viewModel.formState

/**
 * Data validation state of the login form.
 */

sealed class WalletConnectState {
    object Approved : WalletConnectState()
    data class Error(val error: Int) : WalletConnectState()
    object Close : WalletConnectState()
    object Connected : WalletConnectState()
    object Disconnected : WalletConnectState()

}