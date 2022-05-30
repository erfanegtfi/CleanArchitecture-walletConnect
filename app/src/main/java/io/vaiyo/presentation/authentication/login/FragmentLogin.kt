package io.vaiyo.presentation.authentication.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.viewModels
import io.vaiyo.R
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.databinding.FragmentLoginBinding
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.usecase.authentication.AuthenticationFormState
import io.vaiyo.presentation.authentication.WalletConnect
import io.vaiyo.presentation.authentication.di.DaggerAuthenticationComponent
import io.vaiyo.presentation.authentication.viewModel.LoginViewModel
import io.vaiyo.presentation.authentication.viewModel.WalletConnectViewModel
import io.vaiyo.presentation.authentication.viewModel.formState.WalletConnectState
import io.vaiyo.presentation.base.BaseFragment
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.common.gone
import io.vaiyo.presentation.common.onMessageToast
import io.vaiyo.presentation.common.viewBindingDelegate.viewBinding
import io.vaiyo.presentation.common.visible
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.walletconnect.nullOnThrow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FragmentLogin : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }
    private val walletConnectViewModel: WalletConnectViewModel by viewModels { viewModelFactory }

    override val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        DaggerAuthenticationComponent.factory().create(requireActivity().findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        walletConnectViewModel.walletConnectLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is WalletConnectState.Approved -> {
                    hideLoading()
                    onConnectionApproved()
                    registerUser()
                }
                is WalletConnectState.Close -> {
                    hideLoading()
                    onConnectionClosed()
                }
                is WalletConnectState.Connected -> {
                    showLoading()
                }
                is WalletConnectState.Disconnected -> {
                    hideLoading()
                    onConnectionClosed()
                }
                is WalletConnectState.Error -> {

                }
            }
        })


        viewModel.loginLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Loading -> {
                    showLoading()
                }
                is ViewState.Success -> {
                    hideLoading()
                    onMessageToast(R.string.fragment_login_success_login)
                }
                is ViewState.Failure -> {
                    hideLoading()
                    onMessageToast(it.error)
                }
                else -> {
                }
            }
        })

        viewModel.loginFormState.observe(viewLifecycleOwner, {
            when (it) {
                is AuthenticationFormState.InvalidEmail -> {
                    binding.textEmail.error = getString(it.error)
                }
                is AuthenticationFormState.InvalidPasswordRepeat -> {
                    binding.textPassword.error = getString(it.error)
                }

                else -> {
                }
            }
        })

        binding.btnConnect.setOnClickListener {
            val uri = walletConnectViewModel.getWalletConnectURI(requireContext())
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(uri)
            try {
                startActivity(i)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.btnDisconnect.setOnClickListener {
            Log.v("FragmentLogin", "btnDisconnect ${WalletConnect.session == null}")
            WalletConnect.session?.kill()
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.textEmail.text.toString()
            val pass = binding.textPassword.text.toString()
            viewModel.validateFormAndLoginUser(LoginRequest(email = email, password = pass))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.v("FragmentLogin", "start")
        initialSetup()
    }

    private fun initialSetup() {
        val session = nullOnThrow { WalletConnect.session } ?: return
        onConnectionApproved()
        hideLoading()
    }

    private fun onConnectionApproved() {
        binding.btnDisconnect.visible()
        binding.btnConnect.gone()
        binding.txtWalletAddress.visible()
        if (!WalletConnect.session?.approvedAccounts().isNullOrEmpty())
            binding.txtWalletAddress.text = "Connected: ${WalletConnect.session?.approvedAccounts()}"
    }

    private fun onConnectionClosed() {
        binding.btnDisconnect.gone()
        binding.btnConnect.visible()
        binding.txtWalletAddress.gone()
        binding.txtWalletAddress.text = ""
    }

    private fun registerUser() {
        if (WalletConnect.session?.approvedAccounts() != null
            && !WalletConnect.session?.approvedAccounts().isNullOrEmpty()
            && WalletConnect.session?.peerMeta()?.name != null
        ) {
            viewModel.validateFormAndLoginUser(
                LoginRequest(
                    waddress = WalletConnect.session!!.approvedAccounts()!![0],
                    wtype = WalletConnect.session!!.peerMeta()!!.name!!,
                )
            )
        } else {
            onMessageToast(R.string.fragment_login_wc_approve_error)
        }
    }
}