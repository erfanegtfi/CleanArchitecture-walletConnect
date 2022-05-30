package io.vaiyo.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import io.vaiyo.databinding.ViewRoomPariticipatesBinding
import io.vaiyo.domain.model.Participate
import io.vaiyo.presentation.common.gone
import io.vaiyo.presentation.common.visible
import io.vaiyo.presentation.utils.loadImage


class ParticipateAvatars : FrameLayout, View.OnClickListener {

    var _binding: ViewRoomPariticipatesBinding? = null
    val binding
        get() = _binding

    init {
//    _binding = ViewRoomPariticipatesBinding.inflate(LayoutInflater.from(context), this)
    }

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(
        context: Context, attrs: AttributeSet
    ) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(
        context: Context, attrs: AttributeSet, defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        _binding = ViewRoomPariticipatesBinding.inflate(LayoutInflater.from(context), this)

    }

    fun pars(participantList: List<Participate>?) {
        if (participantList?.isEmpty() == true) {
            _binding?.txtNoParticipate?.visible()
            _binding?.frameParticipate1?.gone()
            _binding?.frameParticipate2?.gone()
            _binding?.frameParticipate3?.gone()
            _binding?.frameParticipate4?.gone()
        }
        else {
            _binding?.txtNoParticipate?.gone()
            participantList?.forEachIndexed { index, element ->
                if (index == 0) {
                    _binding?.frameParticipate1?.visible()
                    _binding?.userAvatar1?.visible()
                    if (element.image?.url != null)
                        _binding?.userAvatar4?.loadImage(element.image.url)
                    else if (!element.name.isNullOrBlank()) {
                        _binding?.userTxt1?.visible()
                        _binding?.userTxt1?.text = element.name.subSequence(0, 1)
                    }
                } else if (index == 1) {
                    _binding?.frameParticipate2?.visible()
                    _binding?.userAvatar2?.visible()
                    if (element.image?.url != null)
                        _binding?.userAvatar4?.loadImage(element.image.url)
                    else if (!element.name.isNullOrBlank()) {
                        _binding?.userTxt2?.visible()
                        _binding?.userTxt2?.text = element.name.subSequence(0, 1)
                    }
                } else if (index == 2) {
                    _binding?.frameParticipate3?.visible()
                    _binding?.userAvatar3?.visible()
                    if (element.image?.url != null)
                        _binding?.userAvatar4?.loadImage(element.image.url)
                    else if (!element.name.isNullOrBlank()) {
                        _binding?.userTxt3?.visible()
                        _binding?.userTxt3?.text = element.name.subSequence(0, 1)
                    }
                } else if (index == 3) {
                    _binding?.frameParticipate4?.visible()
                    _binding?.userAvatar4?.visible()
                    if (participantList.size > 4) {
                        _binding?.userTxt4?.visible()
                        _binding?.userTxt4?.text = "+${participantList.size - 3}"
                    } else {
                        if (element.image?.url != null) {
                            _binding?.userAvatar4?.loadImage(element.image.url)
                        } else if (!element.name.isNullOrBlank()) {
                            _binding?.userTxt4?.visible()
                            _binding?.userTxt4?.text = element.name.subSequence(0, 1)
                        }
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
//        _binding = null
    }
}