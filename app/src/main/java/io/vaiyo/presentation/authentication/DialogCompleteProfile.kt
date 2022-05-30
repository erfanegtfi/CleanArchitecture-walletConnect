package io.vaiyo.presentation.authentication

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import io.vaiyo.R
import io.vaiyo.databinding.DialogFragmentAddWalletBinding
import io.vaiyo.databinding.DialogRoomMenuBinding
import io.vaiyo.presentation.base.BaseDialogFragment
import io.vaiyo.presentation.common.dataBindingDelegate.DialogFragmentDataBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DialogCompleteProfile : BaseDialogFragment<DialogFragmentAddWalletBinding>() {


    override val binding: DialogFragmentAddWalletBinding by DialogFragmentDataBinding(R.layout.dialog_fragment_complete_profile)

    companion object {

        fun newInstance() = DialogCompleteProfile().apply {
            arguments = bundleOf(

            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

        }



    }

}