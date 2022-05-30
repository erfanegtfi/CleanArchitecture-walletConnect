package io.vaiyo.data.repository

import io.vaiyo.data.dataSource.remote.api.AuthenticationApi
import io.vaiyo.data.dataSource.remote.util.getResult
import io.vaiyo.data.mapper.MapperAuthenticationEntityDomainModel
import io.vaiyo.domain.abstraction.SessionManager
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.requests.RegisterRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import io.vaiyo.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val mapper: MapperAuthenticationEntityDomainModel,
    private val appPreferencesHelper: SessionManager,
) : AuthenticationRepository {
    override fun login(authenticationRequest: LoginRequest): Flow<ApiCallState<AuthenticationResponse>> = flow {
        when (val response = getResult {
            authenticationApi.login(authenticationRequest)
        }) {
            is ApiCallState.Success -> {
                appPreferencesHelper.saveToken(response.data.jwt)
                appPreferencesHelper.saveUser(response.data.user)
                emit(ApiCallState.Success(mapper.mapFromEntity(response.data)))
            }
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }

    override fun register(authenticationRequest: RegisterRequest): Flow<ApiCallState<AuthenticationResponse>> = flow {
        when (val response = getResult {
            authenticationApi.register(authenticationRequest)
        }) {
            is ApiCallState.Success -> {
                appPreferencesHelper.saveToken(response.data.jwt)
                appPreferencesHelper.saveUser(response.data.user)
                emit(ApiCallState.Success(mapper.mapFromEntity(response.data)))
            }
            is ApiCallState.Failure -> emit(ApiCallState.Failure(response.error))
        }
    }
}