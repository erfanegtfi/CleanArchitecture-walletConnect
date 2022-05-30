package io.vaiyo.presentation.authentication.register


import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.viewModels
import io.vaiyo.R
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.databinding.FragmentRegisterBinding
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.requests.RegisterRequest
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.usecase.authentication.AuthenticationFormState
import io.vaiyo.presentation.authentication.di.DaggerAuthenticationComponent
import io.vaiyo.presentation.authentication.viewModel.RegisterViewModel
import io.vaiyo.presentation.authentication.viewModel.WalletConnectViewModel
import io.vaiyo.presentation.base.BaseFragment
import io.vaiyo.presentation.common.viewBindingDelegate.viewBinding
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.common.onMessageToast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
class FragmentRegister : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    val viewModel: RegisterViewModel by viewModels { viewModelFactory }
//    private val walletConnectViewModel: WalletConnectViewModel by viewModels { viewModelFactory }

    override val binding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        DaggerAuthenticationComponent.factory().create(requireActivity().findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.registerLiveData.observe(viewLifecycleOwner, {
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

        viewModel.registerState.observe(viewLifecycleOwner, {
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

        binding.btnRegister.setOnClickListener {
            val username = binding.textUsername.text.toString()
            val email = binding.textEmail.text.toString()
            val password = binding.textPassword.text.toString()
            val affiliate = binding.textAffiliate.text.toString()

            viewModel.validateFormAndRegisterUser(RegisterRequest(email = email, password = password))

        }
//        binding.haveAnAccountLink.setOnClickListener {
////            val navOptions = NavOptions.Builder().setPopUpTo(R.id.register_dest, true).build()
//            findNavController().navigate(R.id.action_register_to_login)
//        }
    }
}