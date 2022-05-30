package io.vaiyo.presentation.meeting.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.vaiyo.R
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.meeting.di.DaggerMeetingComponent

class JoinRoomBottomSheet : BottomSheetDialogFragment() {

    private var mBehavior: BottomSheetBehavior<View>? = null


    companion object {

        fun newInstance(): JoinRoomBottomSheet {
            return JoinRoomBottomSheet().apply {
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

        return inflater.inflate(R.layout.bottom_sheet_join_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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