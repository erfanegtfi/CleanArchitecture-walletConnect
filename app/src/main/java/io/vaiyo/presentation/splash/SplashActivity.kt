package io.vaiyo.presentation.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import io.vaiyo.R
import io.vaiyo.presentation.splash.viewModel.SplashViewModel
import io.vaiyo.data.dataSource.di.viewModel.AppViewModelFactory
import io.vaiyo.databinding.ActivitySplashBinding
import io.vaiyo.presentation.authentication.AuthenticationActivity
import io.vaiyo.presentation.base.BaseActivity
import io.vaiyo.presentation.common.dataBindingDelegate.ActivityDataBinding
import io.vaiyo.presentation.common.viewBindingDelegate.viewBinding
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.splash.di.DaggerSplashComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    override val binding: ActivitySplashBinding by ActivityDataBinding(R.layout.activity_splash)
    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    companion object {

        fun start(context: Context) {
            Intent(context, SplashActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                context.startActivity(this)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerSplashComponent.factory().create(findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(3000)
            AuthenticationActivity.start(this@SplashActivity)
            finish()
        }

    }

}