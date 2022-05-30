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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.vaiyo.domain.utils.GeneralError


abstract class BaseBottomSheetFragment<VB : ViewDataBinding> : BottomSheetDialogFragment() {


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
