package io.vaiyo.presentation.authentication

import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.navigation.fragment.findNavController
import io.vaiyo.R
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.databinding.FragmentAuthenticationBinding
import io.vaiyo.presentation.authentication.di.DaggerAuthenticationComponent
import io.vaiyo.presentation.base.BaseFragment
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.common.viewBindingDelegate.viewBinding
import io.vaiyo.presentation.meeting.list.MeetingListActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class FragmentAuthentication :
    BaseFragment<FragmentAuthenticationBinding>(R.layout.fragment_authentication) {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    override val binding: FragmentAuthenticationBinding by viewBinding(FragmentAuthenticationBinding::bind)

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        DaggerAuthenticationComponent.factory().create(requireActivity().findAppComponent())
            .inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_authentication_dest_to_login_dest)
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_authentication_dest_to_register_dest)
        }

        binding.btnGuest.setOnClickListener {
            MeetingListActivity.start(requireContext())
            activity?.finish()
//            val createPoll = DialogWalletConnect.newInstance()
//            createPoll.show(childFragmentManager, "")
        }

    }
}