package io.vaiyo.presentation.meeting.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.vaiyo.R
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.databinding.BottomSheetCreateRoomBinding
import io.vaiyo.domain.model.requests.CreateRoomRequest
import io.vaiyo.domain.usecase.createRoom.CreateMeetingFormState
import io.vaiyo.presentation.base.BaseBottomSheetFragment
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.common.dataBindingDelegate.BottomSheetFragmentDataBinding
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.common.onMessageToast
import io.vaiyo.presentation.common.removeEditTextError
import io.vaiyo.presentation.common.setEditTextError
import io.vaiyo.presentation.meeting.di.DaggerMeetingComponent
import io.vaiyo.presentation.meeting.list.MeetingListActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CreateRoomBottomSheet : BaseBottomSheetFragment<BottomSheetCreateRoomBinding>() {

    private var mBehavior: BottomSheetBehavior<View>? = null
    override val binding: BottomSheetCreateRoomBinding by BottomSheetFragmentDataBinding(R.layout.bottom_sheet_create_room)

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    val viewModel: CreateMeetingViewModel by viewModels { viewModelFactory }

    companion object {

        fun newInstance(): CreateRoomBottomSheet {
            return CreateRoomBottomSheet().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMeetingComponent.factory().create(requireActivity().findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.createMeetingFormState.observe(viewLifecycleOwner, {
            when (it) {
                is CreateMeetingFormState.IsDataValid -> {
                    binding.textRoomName.removeEditTextError()
                }
                is CreateMeetingFormState.MeetingNameError -> {
                    binding.textRoomName.setEditTextError(R.string.error_empty_input)
                }
            }
        })

        viewModel.createMeetingLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Success -> {
                    hideLoading()
                    (context as MeetingListActivity).refreshRoom()
                    dismiss()
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

        binding.btnCreateMeeting.setOnClickListener {
            viewModel.validateFormAndCreateRoom(
                CreateRoomRequest(
                    binding.textRoomName.text.toString(),
                    if (binding.switchMuteOnJoin.isChecked) "1" else "0",
                    if (binding.switchRequireModeratorApproval.isChecked) "1" else "0",
                    if (binding.switchAnyoneCanStart.isChecked) "1" else "0",
                    if (binding.switchAllJoinModerator.isChecked) "1" else "0",
                    if (binding.switchRecording.isChecked) "1" else "0",
                )
            )
        }

        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheetInternal = d.findViewById<View>(R.id.design_bottom_sheet)
            if (bottomSheetInternal != null) {
                mBehavior = BottomSheetBehavior.from(bottomSheetInternal)
            }
        }
    }


    override fun onResume() {
        super.onResume()

    }


}