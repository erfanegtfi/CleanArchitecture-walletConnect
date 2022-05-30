package io.vaiyo.presentation

import android.app.Application
import com.squareup.moshi.Moshi
import io.vaiyo.data.dataSource.di.component.AppComponent
import io.vaiyo.data.dataSource.di.component.DaggerAppComponent



class App : Application() {

    lateinit var component: AppComponent


    override fun onCreate() {
        super.onCreate()

        createComponent()
        component.inject(this)

    }

    private fun createComponent() {
        component = DaggerAppComponent.factory().create(this);
    }

    fun getAppComponent(): AppComponent {
        return component
    }




}