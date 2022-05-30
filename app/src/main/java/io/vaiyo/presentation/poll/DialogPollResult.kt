package io.vaiyo.presentation.poll

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import io.vaiyo.R
import io.vaiyo.databinding.DialogCreatePollBinding
import io.vaiyo.databinding.DialogPollResultBinding
import io.vaiyo.presentation.base.BaseDialogFragment
import io.vaiyo.presentation.common.dataBindingDelegate.DialogFragmentDataBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DialogPollResult : BaseDialogFragment<DialogPollResultBinding>() {


    override val binding: DialogPollResultBinding by DialogFragmentDataBinding(R.layout.dialog_poll_result)

    companion object {

        fun newInstance() = DialogPollResult().apply {
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