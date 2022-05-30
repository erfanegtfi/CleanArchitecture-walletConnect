package io.vaiyo.domain.repository

import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.requests.RegisterRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun login(authenticationRequest: LoginRequest): Flow<ApiCallState<AuthenticationResponse>>
    fun register(authenticationRequest: RegisterRequest): Flow<ApiCallState<AuthenticationResponse>>
}