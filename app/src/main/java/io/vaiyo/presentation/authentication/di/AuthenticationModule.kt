package io.vaiyo.presentation.authentication.di


import dagger.Module
import dagger.Provides
import io.vaiyo.data.dataSource.remote.api.AuthenticationApi
import io.vaiyo.data.dataSource.remote.api.MeetingApi
import io.vaiyo.data.repository.AuthenticationRepositoryImp
import io.vaiyo.domain.repository.AuthenticationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit


@Module
@ExperimentalCoroutinesApi
class AuthenticationModule {


    @Provides
    fun provideAuthenticationRepository(authenticationRepository: AuthenticationRepositoryImp): AuthenticationRepository {
        return authenticationRepository
    }

    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi = retrofit.create(AuthenticationApi::class.java)

}