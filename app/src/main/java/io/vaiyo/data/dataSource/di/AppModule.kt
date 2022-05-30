package io.vaiyo.data.dataSource.di


import androidx.annotation.NonNull
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.vaiyo.BuildConfig
import io.vaiyo.data.dataSource.remote.NetworkConfig.BASE_URL
import io.vaiyo.data.dataSource.remote.NetworkConfig.REQUEST_TIMEOUT_DURATION
import io.vaiyo.data.dataSource.remote.interceptor.AuthenticatorInterceptor
import io.vaiyo.data.dataSource.remote.interceptor.NetInterceptor
import io.vaiyo.domain.abstraction.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(sessionManager: SessionManager): OkHttpClient {

        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(AuthenticatorInterceptor(sessionManager))
                .addInterceptor(NetInterceptor())
                .connectTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
                .build()
        }

    }


    @Provides
//    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create()
    }


    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
//            .addConverterFactory(SimpleXmlConverterFactory.create())
//            .addConverterFactory(JaxbConverterFactory.create())
//            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

}