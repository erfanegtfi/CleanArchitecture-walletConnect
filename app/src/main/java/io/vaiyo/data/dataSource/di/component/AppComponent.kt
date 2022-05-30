package io.vaiyo.data.dataSource.di.component

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import io.vaiyo.data.dataSource.di.AppModule
import io.vaiyo.data.dataSource.di.PersistenceModule
import io.vaiyo.data.dataSource.di.viewModel.ViewModelFactoryModule
import io.vaiyo.data.dataSource.di.viewModel.ViewModelModule
import io.vaiyo.data.dataSource.local.preferences.Session
import io.vaiyo.domain.abstraction.SessionManager
import io.vaiyo.presentation.App
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, ViewModelFactoryModule::class, ViewModelModule::class, PersistenceModule::class] //
)
@ExperimentalCoroutinesApi
interface AppComponent {
    fun inject(app: App)

    fun provideRetrofit(): Retrofit
    fun provideOkHttpClient(): OkHttpClient
    fun provideGson(): Gson

    fun bindViewModelFactory(): ViewModelProvider.Factory

    fun getContext(): Context
    fun provideAppSession(): Session
    fun provideSessionManager(): SessionManager

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppComponent
    }
}