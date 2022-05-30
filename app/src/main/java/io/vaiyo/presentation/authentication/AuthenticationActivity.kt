package io.vaiyo.presentation.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.vaiyo.R
import io.vaiyo.databinding.ActivityAuthenticationBinding
import io.vaiyo.presentation.authentication.di.DaggerAuthenticationComponent
import io.vaiyo.presentation.base.BaseActivity
import io.vaiyo.presentation.common.dataBindingDelegate.ActivityDataBinding
import io.vaiyo.presentation.common.findAppComponent
import io.vaiyo.presentation.utils.HashUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>(){

    override val binding: ActivityAuthenticationBinding by ActivityDataBinding(R.layout.activity_authentication)

    companion object {

        fun start(context: Context) {
            Intent(context, AuthenticationActivity::class.java).apply {
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAuthenticationComponent.factory().create(findAppComponent()).inject(this)
        super.onCreate(savedInstanceState)

//      var tt =  HashUtils.sha1("isMeetingRunningmeetingID=random-5689815qZxhjoeyU3xTdgSFWJsZdhqHjzNwV549H4hpaeQI")
//        Log.v("tttttt", tt)
    }

}