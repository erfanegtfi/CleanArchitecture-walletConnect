package io.vaiyo.presentation.meeting.menu

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import io.vaiyo.R
import io.vaiyo.databinding.DialogRoomMenuBinding
import io.vaiyo.presentation.base.BaseDialogFragment
import io.vaiyo.presentation.common.dataBindingDelegate.DialogFragmentDataBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DialogRoomMenu : BaseDialogFragment<DialogRoomMenuBinding>() {


    override val binding: DialogRoomMenuBinding by DialogFragmentDataBinding(R.layout.dialog_room_menu)

    companion object {

        fun newInstance() = DialogRoomMenu().apply {
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

        binding.lntShareExternalVideo.lntWrapper.setOnClickListener {
        }

        binding.lntStartPoll.lntWrapper.setOnClickListener {
        }
        binding.lntUploadPresentation.lntWrapper.setOnClickListener {
        }

    }

}