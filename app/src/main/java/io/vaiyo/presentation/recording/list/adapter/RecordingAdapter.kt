package io.vaiyo.presentation.recording.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.vaiyo.R
import io.vaiyo.databinding.ItemRecordingBinding
import io.vaiyo.domain.model.response.Recording

class RecordingAdapter (private val properties: List<Recording>, val context: Context, private val adapterCallBack: (position: Int, view: Int?) -> Unit) :
    RecyclerView.Adapter<RecordingListItemViewHolder>() {

    override fun getItemCount(): Int {
        return properties.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordingListItemViewHolder {
        val view: ItemRecordingBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recording, parent, false)
        return RecordingListItemViewHolder(view, context, adapterCallBack)
    }

//    private fun adapterCallBack(position: Int, view: Int?) {
//
//    }

    override fun onBindViewHolder(holder: RecordingListItemViewHolder, position: Int) {
        holder.bind(properties[position])
    }
}