package io.vaiyo.presentation.meeting.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.vaiyo.R
import io.vaiyo.databinding.ItemMeetingBinding
import io.vaiyo.domain.model.Room
import io.vaiyo.domain.model.response.Meeting

class MeetingAdapter (private val properties: List<Room>, val context: Context, private val adapterCallBack: (position: Int, view: Int?) -> Unit) :
    RecyclerView.Adapter<MeetingListItemViewHolder>() {

    override fun getItemCount(): Int {
        return properties.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingListItemViewHolder {
        val view: ItemMeetingBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_meeting, parent, false)
        return MeetingListItemViewHolder(view, context, adapterCallBack)
    }

//    private fun adapterCallBack(position: Int, view: Int?) {
//
//    }

    override fun onBindViewHolder(holder: MeetingListItemViewHolder, position: Int) {
        holder.bind(properties[position])
    }
}