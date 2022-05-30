package io.vaiyo.presentation.recording.list.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.vaiyo.databinding.ItemRecordingBinding
import io.vaiyo.domain.model.response.Recording

class RecordingListItemViewHolder(
    private val binding: ItemRecordingBinding,
    private val context: Context,
    private val adapterCallBack: (position: Int, view: Int?) -> Unit,
) : RecyclerView.ViewHolder(
    binding.root
), View.OnClickListener {

    fun bind(model: Recording) {
        binding.recording = model
        binding.imageDelete.setOnClickListener(this)
        binding.root.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null)
            adapterCallBack(adapterPosition, v.id)
    }
}