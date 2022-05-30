package io.vaiyo.data.dataSource.remote.api

import io.vaiyo.data.dataSource.remote.NetworkEndpoint
import io.vaiyo.data.entity.response.AuthenticationResponseEntity
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.requests.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST(NetworkEndpoint.LOGIN)
    suspend fun login(@Body authenticationRequest: LoginRequest): Response<AuthenticationResponseEntity>

    @POST(NetworkEndpoint.REGISTER)
    suspend fun register(@Body authenticationRequest: RegisterRequest): Response<AuthenticationResponseEntity>
}