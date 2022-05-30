package io.vaiyo.presentation.meeting.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import io.vaiyo.R
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.databinding.DialogRoomListMenuBinding
import io.vaiyo.presentation.base.BaseDialogFragment
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.common.dataBindingDelegate.DialogFragmentDataBinding
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.common.onMessageToast
import io.vaiyo.presentation.meeting.di.DaggerMeetingComponent
import io.vaiyo.presentation.recording.list.RecordListActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DialogRoomListMenu : BaseDialogFragment<DialogRoomListMenuBinding>() {

    override val binding: DialogRoomListMenuBinding by DialogFragmentDataBinding(R.layout.dialog_room_list_menu)
    var roomID: String? = null

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    val viewModel: RoomListMenuViewModel by viewModels { viewModelFactory }

    companion object {

        const val ROOM_ID = "ROOM_ID"

        fun newInstance(roomID: String) = DialogRoomListMenu().apply {
            arguments = bundleOf(
                ROOM_ID to roomID
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMeetingComponent.factory().create(requireActivity().findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()

        arguments?.let {
            roomID = it.getString(ROOM_ID)
        }

        viewModel.removeRoomLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    hideLoading()
                    (context as MeetingListActivity).refreshRoom()
                }
                is ViewState.Failure -> {
                    hideLoading()
                    onMessageToast(it.error)
                }
                is ViewState.Loading -> {
                    showLoading()
                }
            }
        })

        binding.lntRecordings.lntWrapper.setOnClickListener {
            if (roomID != null) {
                RecordListActivity.start(roomID!!, requireContext())
                this.dismiss()
            }
        }
        binding.lntDelete.lntWrapper.setOnClickListener {
            deleteRecordConfirm()
        }
    }

    private fun deleteRecordConfirm() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_room_list_menu_delete_confirm_title))
            .setMessage(getString(R.string.dialog_room_list_menu_delete_confirm_message))
            .setPositiveButton(R.string.dialog_room_list_menu_delete_btn_delete) { _, _ ->
                viewModel.deleteRoom()
            }
            .setNegativeButton(R.string.dialog_room_list_menu_delete_btn_cancel, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    private fun setupClickListeners() {

    }

}