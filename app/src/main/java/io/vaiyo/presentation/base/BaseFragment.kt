package io.vaiyo.presentation.base;


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import cn.pedant.SweetAlert.SweetAlertDialog
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.common.onMessageSnackbar
import kotlinx.coroutines.flow.collectLatest


abstract class BaseFragment<VB : ViewBinding>(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId) {


    protected abstract val binding: VB
    private var viewActions: SweetAlertDialog? = null


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun subscribeApiCallListener(baseViewModel: BaseViewModel?) {
        lifecycleScope.launchWhenStarted {
            baseViewModel?.sharedApiCallEvents?.collectLatest {
                when (it) {
                    is ViewState.Loading -> {
                        showLoading()
                    }
                    is ViewState.Success -> {
                        hideLoading()
                    }
                    is ViewState.Failure -> { // on errors
                        hideLoading()
                        onMessageSnackbar(it.error)
                    }
                }
            }
        }
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
