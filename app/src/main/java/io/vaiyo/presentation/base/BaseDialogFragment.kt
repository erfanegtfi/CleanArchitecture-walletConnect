package io.vaiyo.presentation.base;


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.Nullable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import cn.pedant.SweetAlert.SweetAlertDialog
import io.vaiyo.domain.utils.GeneralError


abstract class BaseDialogFragment<VB : ViewDataBinding> : DialogFragment() {


    protected abstract val binding: VB
    private var viewActions: SweetAlertDialog? = null


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
//        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }


    fun showLoading() {
        viewActions = BaseViewActions.getSweetAlertDialog(requireContext())
        BaseViewActions.showLoading(viewActions!!)
    }

    fun hideLoading() {
        if (viewActions != null)
            BaseViewActions.hideLoading(viewActions!!)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
