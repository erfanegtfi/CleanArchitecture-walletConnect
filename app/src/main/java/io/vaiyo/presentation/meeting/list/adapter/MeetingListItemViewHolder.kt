package io.vaiyo.presentation.meeting.list.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.vaiyo.databinding.ItemMeetingBinding
import io.vaiyo.databinding.ViewRoomPariticipatesBinding
import io.vaiyo.domain.model.Participate
import io.vaiyo.domain.model.Room
import io.vaiyo.presentation.common.visible

class MeetingListItemViewHolder(
    private val binding: ItemMeetingBinding,
    private val context: Context,
    private val adapterCallBack: (position: Int, view: Int?) -> Unit,
) : RecyclerView.ViewHolder(
    binding.root
), View.OnClickListener {

    fun bind(model: Room) {
        binding.room = model
        binding.participates.pars(model.participates)
        binding.imageMenu.setOnClickListener(this)
        binding.imageCopy.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null)
            adapterCallBack(adapterPosition, v.id)
    }
}