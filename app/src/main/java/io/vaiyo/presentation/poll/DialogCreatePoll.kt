package io.vaiyo.presentation.poll

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import io.vaiyo.R
import io.vaiyo.databinding.DialogCreatePollBinding
import io.vaiyo.presentation.base.BaseDialogFragment
import io.vaiyo.presentation.common.dataBindingDelegate.DialogFragmentDataBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DialogCreatePoll : BaseDialogFragment<DialogCreatePollBinding>() {


    override val binding: DialogCreatePollBinding by DialogFragmentDataBinding(R.layout.dialog_create_poll)

    companion object {

        fun newInstance() = DialogCreatePoll().apply {
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

        binding.lntPollYesNo.lntWrapper.setOnClickListener {
            changePollTypeView(1)
        }
        binding.lntPollTrueFalse.lntWrapper.setOnClickListener {
            changePollTypeView(2)
        }
        binding.lntPollAbc.lntWrapper.setOnClickListener {
            changePollTypeView(3)
        }
        binding.lntPollAbcd.lntWrapper.setOnClickListener {
            changePollTypeView(4)
        }
    }

    private fun changePollTypeView(type: Int) {
        binding.lntPollYesNo.img.setImageResource(R.drawable.ic_img_unselected_radio_btn)
        binding.lntPollTrueFalse.img.setImageResource(R.drawable.ic_img_unselected_radio_btn)
        binding.lntPollAbc.img.setImageResource(R.drawable.ic_img_unselected_radio_btn)
        binding.lntPollAbcd.img.setImageResource(R.drawable.ic_img_unselected_radio_btn)

        if (type == 1)
            binding.lntPollYesNo.img.setImageResource(R.drawable.ic_img_selected_radio_btn)
        if (type == 2)
            binding.lntPollTrueFalse.img.setImageResource(R.drawable.ic_img_selected_radio_btn)
        if (type == 3)
            binding.lntPollAbc.img.setImageResource(R.drawable.ic_img_selected_radio_btn)
        if (type == 4)
            binding.lntPollAbcd.img.setImageResource(R.drawable.ic_img_selected_radio_btn)
    }

}