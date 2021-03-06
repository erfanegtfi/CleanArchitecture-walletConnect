package io.vaiyo.presentation.base;

import android.os.Bundle;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import cn.pedant.SweetAlert.SweetAlertDialog
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.presentation.common.onMessageToast
import kotlinx.coroutines.flow.collectLatest
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


abstract class BaseActivity<VB : ViewDataBinding>() : AppCompatActivity() {

    private var viewActions: SweetAlertDialog? = null
    protected abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(binding.root)
        EventBus.getDefault().register(this)
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
                        onMessageToast(it.error.message)
                    }
                }
            }
        }
    }

    fun showLoading() {
        viewActions = BaseViewActions.getSweetAlertDialog(this)
        BaseViewActions.showLoading(viewActions!!)
    }

    fun hideLoading() {
        if (viewActions != null)
            BaseViewActions.hideLoading(viewActions!!)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy();
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    override fun onBackPressed() {
        super.onBackPressed();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: Any) {
    }
}
