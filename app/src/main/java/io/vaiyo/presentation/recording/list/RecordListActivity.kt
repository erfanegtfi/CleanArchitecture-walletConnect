package io.vaiyo.presentation.recording.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import io.vaiyo.R
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.data.dataSource.remote.NetworkConfig
import io.vaiyo.databinding.ActivityRecordingListBinding
import io.vaiyo.domain.model.response.*
import io.vaiyo.presentation.base.BaseActivity
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.common.dataBindingDelegate.ActivityDataBinding
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.recording.di.DaggerRecordingComponent
import io.vaiyo.presentation.recording.list.adapter.RecordingAdapter
import io.vaiyo.presentation.utils.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RecordListActivity : BaseActivity<ActivityRecordingListBinding>() {

    override val binding: ActivityRecordingListBinding by ActivityDataBinding(R.layout.activity_recording_list)

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    val viewModel: RecordsListViewModel by viewModels { viewModelFactory }
    val recordings = mutableListOf<Recording>()
    private var meetingID: String? = null

    companion object {

        val MEETING_ID_KEY = "MEETING_ID_KEY"

        fun start(meetingID: String, context: Context) {
            Intent(context, RecordListActivity::class.java).apply {
                val bundle = bundleOf(
                    MEETING_ID_KEY to meetingID,
                )
                putExtras(bundle)
                context.startActivity(this)
            }
        }
    }

    private fun handleBundle(bundle: Bundle?) {
        meetingID = bundle?.getString(MEETING_ID_KEY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerRecordingComponent.factory().create(findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        handleBundle(intent.extras)

        val recordingAdapter = RecordingAdapter(
            properties = recordings,
            context = this,
            adapterCallBack = ::adapterCallback
        )
        binding.rvRecording.layoutManager = LinearLayoutManager(this)
        binding.rvRecording.adapter = recordingAdapter

        viewModel.recordingListLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    recordings.clear()
                    recordings.addAll(it.data)
                    recordingAdapter.notifyDataSetChanged()
                }
                is ViewState.Failure -> {
                }
                is ViewState.Loading -> {
                }
            }
        })
        meetingID?.let {
            viewModel.getRecordingList(it)
        }

    }

    private fun adapterCallback(i: Int, v: Int?) {
        if (v == R.id.imageDelete) {
            if (recordings[i].meetingID != null && recordings[i].recordID != null)
                deleteRecordConfirm(recordings[i].meetingID!!, recordings[i].recordID!!)
        } else {
            Utils.openUrl(NetworkConfig.BASE_URL_RECORDING + recordings[i].recordID, this)
        }
    }

    private fun deleteRecordConfirm(meetingID: String, recordID: String) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.activity_recording_delete_title))
            .setMessage(getString(R.string.activity_recording_delete_message))
            .setPositiveButton(R.string.activity_recording_delete_btn_delete) { _, _ ->
                viewModel.deleteRecording(meetingID, recordID)
            }
            .setNegativeButton(R.string.activity_recording_delete_btn_cancel, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

}