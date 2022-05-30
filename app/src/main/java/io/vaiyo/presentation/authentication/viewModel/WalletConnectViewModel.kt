package io.vaiyo.presentation.authentication.viewModel;

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.vaiyo.presentation.authentication.WalletConnect
import io.vaiyo.presentation.authentication.viewModel.formState.WalletConnectState
import io.vaiyo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import org.walletconnect.Session
import javax.inject.Inject

@ExperimentalCoroutinesApi
@SuppressLint("CheckResult")
class WalletConnectViewModel
@Inject constructor(
) : BaseViewModel(), Session.Callback {


    private val _walletConnectLiveData = MutableLiveData<WalletConnectState>()
    val walletConnectLiveData: LiveData<WalletConnectState>
        get() = _walletConnectLiveData


    fun getWalletConnectURI(context: Context): String? {
        WalletConnect.resetSession(context)
        WalletConnect.session?.addCallback(this)

        return WalletConnect.config?.toWCUri()
    }

    private fun handleStatus(status: Session.Status) {
        Log.v("FragmentLogin", status.toString())
        when (status) {
            Session.Status.Approved -> {
                _walletConnectLiveData.postValue(WalletConnectState.Approved)
            }
            Session.Status.Closed -> {
                _walletConnectLiveData.postValue(WalletConnectState.Close)
            }
            Session.Status.Connected -> {
                _walletConnectLiveData.postValue(WalletConnectState.Connected)
            }
            Session.Status.Disconnected -> {
                _walletConnectLiveData.postValue(WalletConnectState.Disconnected)
            }
            is Session.Status.Error -> {

            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        WalletConnect.session?.removeCallback(this)
        viewModelScope.cancel()
    }

    override fun onMethodCall(call: Session.MethodCall) {

    }

    override fun onStatus(status: Session.Status) {
        handleStatus(status)
    }

}