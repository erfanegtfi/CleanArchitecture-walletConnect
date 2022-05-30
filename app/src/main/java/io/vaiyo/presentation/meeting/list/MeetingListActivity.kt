package io.vaiyo.presentation.meeting.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import io.vaiyo.R
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.databinding.ActivityMeetingListBinding
import io.vaiyo.domain.model.Room
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.base.BaseActivity
import io.vaiyo.presentation.common.viewBindingDelegate.viewBinding
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.meeting.create.CreateRoomBottomSheet
import io.vaiyo.presentation.meeting.di.DaggerMeetingComponent
import io.vaiyo.presentation.meeting.list.adapter.MeetingAdapter
import io.vaiyo.presentation.utils.Utils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MeetingListActivity : BaseActivity<ActivityMeetingListBinding>() {

    override val binding: ActivityMeetingListBinding by viewBinding(ActivityMeetingListBinding::inflate)

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    val viewModel: RoomListViewModel by viewModels { viewModelFactory }

    var bookList: ArrayList<Room> = arrayListOf()

    companion object {

        fun start(context: Context) {
            Intent(context, MeetingListActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMeetingComponent.factory().create(findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
        subscribeApiCallListener(viewModel)
        val meetingAdapter = MeetingAdapter(bookList, this, ::onItemClick)
        binding.rvRooms.layoutManager = LinearLayoutManager(this)
        binding.rvRooms.adapter = meetingAdapter

        viewModel.roomListLiveData.observe(this, {
            when (it) {
                is ViewState.Success -> {
                    binding.loading.hideProgress()
                    bookList.clear()
                    bookList.addAll(it.data)
                    meetingAdapter.notifyDataSetChanged()
                }
                is ViewState.Failure -> {
                    binding.loading.error()
                }
                is ViewState.Loading -> {
                    binding.loading.loading()
                }
            }
        })

        viewModel.getRoomList()


        binding.lntCreateRoom.setOnClickListener {
            CreateRoomBottomSheet.newInstance().show(supportFragmentManager, "")
        }
    }

    fun refreshRoom(){
        viewModel.getRoomList()
    }

    private fun onItemClick(position: Int, view: Int?) {
        if (bookList.size > position) {
            val room = bookList[position]
            if (view == R.id.imageMenu) {
                if (room.bbbId != null) {
                    val menu = DialogRoomListMenu.newInstance(room.bbbId)
                    menu.show(supportFragmentManager, "")
                }
            } else if (view == R.id.imageCopy) {
                Utils.copyToClipboard(this, room.roomLink)
            }
        }
    }
}