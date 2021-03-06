package io.vaiyo.presentation.common.dataBindingDelegate

import androidx.lifecycle.Lifecycle
import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class FragmentDataBinding<out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : ReadOnlyProperty<Fragment, T>, LifecycleObserver {

    private var binding: T? = null
    private var thisRef: Fragment? = null


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        this.thisRef?.viewLifecycleOwner?.lifecycle?.removeObserver(this)

        // Fragment.viewLifecycleOwner call LifecycleObserver.onDestroy() before Fragment.onDestroyView().
        // That's why we need to postpone reset of the viewBinding
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post {
            Log.v("onDestroyonDestroyyy", "onDestroyonDestroy")
            binding = null
        }
    }


    override operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        this.thisRef = thisRef
        thisRef.viewLifecycleOwner.lifecycle.addObserver(this)

        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return binding ?: createBinding(thisRef).also { binding = it }
    }

    private fun createBinding(fragment: Fragment): T {
        return DataBindingUtil.inflate(fragment.layoutInflater, layoutRes, null, false)
    }
}
