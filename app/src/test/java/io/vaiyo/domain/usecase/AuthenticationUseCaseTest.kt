package io.vaiyo.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth
import io.mockk.*
import io.vaiyo.data.dataSource.remote.api.AuthenticationApi
import io.vaiyo.data.entity.UserEntity
import io.vaiyo.data.entity.response.AuthenticationResponseEntity
import io.vaiyo.data.mapper.MapperAuthenticationEntityDomainModel
import io.vaiyo.data.repository.AuthenticationRepositoryImp
import io.vaiyo.domain.abstraction.SessionManager
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.repository.AuthenticationRepository
import io.vaiyo.domain.usecase.authentication.AuthenticationUseCase
import io.vaiyo.domain.utils.ErrorApp
import io.vaiyo.domain.utils.GeneralError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AuthenticationUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var authenticationRepository: AuthenticationRepository
    private lateinit var authenticationUseCase: AuthenticationUseCase
    private lateinit var sessionManager: SessionManager
    private lateinit var authenticationApi: AuthenticationApi

    @Before
    fun setup() {
        authenticationApi = mockk()
        sessionManager = mockk()

        val mapperAuthenticationEntityDomainModel = MapperAuthenticationEntityDomainModel()
        authenticationRepository = AuthenticationRepositoryImp(authenticationApi, mapperAuthenticationEntityDomainModel, sessionManager)
        authenticationUseCase = AuthenticationUseCase(repository = authenticationRepository)


    }

    private lateinit var authenticationRequest: LoginRequest
    private lateinit var authenticationResponseEntity: AuthenticationResponseEntity
    private lateinit var loginResponse: AuthenticationResponse

    @Test
    fun `authentication use case for login success response`() = runBlockingTest {
        givenAuthInputsForSuccess()
        val authenticationFlow = whenInvokeUseCaseForSuccess()
        thenFlowEmitViewStateItemsForSuccess(authenticationFlow)
    }

    private fun givenAuthInputsForSuccess() {
        authenticationRequest = LoginRequest("0xA2cA38CBcE6c521860D30C214f2a331F46B81cE7", "metamask")
        authenticationResponseEntity = AuthenticationResponseEntity(jwt = "jwt", user = UserEntity())
        loginResponse = AuthenticationResponse(jwt = "jwt", user = User())
        authenticationResponseEntity.status = 200
        coEvery {
            authenticationApi.authentication(authenticationRequest)
        } returns Response.success(authenticationResponseEntity)

        every {
            sessionManager.saveToken(any())
        } just Runs

        every {
            sessionManager.saveUser(any())
        } just Runs
    }

    private fun whenInvokeUseCaseForSuccess(): Flow<ViewState<AuthenticationResponse>> {
        return authenticationUseCase.action(authenticationRequest)
    }

    private suspend fun thenFlowEmitViewStateItemsForSuccess(authenticationFlow: Flow<ViewState<AuthenticationResponse>>) {
        authenticationFlow.test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(ViewState.Loading)
            val emission2 = awaitItem()
            Truth.assertThat(emission2).isEqualTo(ViewState.Success(data = loginResponse))
            awaitComplete()
        }
    }

    @Test
    fun `authentication use case for login failure response`() = runBlockingTest {
        givenAuthInputsForFailure()
        val authenticationFlow = whenInvokeUseCaseForFailure()
        thenFlowEmitViewStateItemsForFailure(authenticationFlow)
    }

    private fun givenAuthInputsForFailure() {
        authenticationRequest = LoginRequest("0xA2cA38CBcE6c521860D30C214f2a331F46B81cE7", "metamask")

        val jsonObject = JSONObject()
        jsonObject.put("status", 500)
        jsonObject.put("message", "error message")
        val body = jsonObject.toString().toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull())

        coEvery {
            authenticationApi.authentication(authenticationRequest)
        } returns Response.error(500, body)

    }

    private fun whenInvokeUseCaseForFailure(): Flow<ViewState<AuthenticationResponse>> {
        return authenticationUseCase.action(authenticationRequest)
    }

    private suspend fun thenFlowEmitViewStateItemsForFailure(authenticationFlow: Flow<ViewState<AuthenticationResponse>>) {
        val generalError = GeneralError(errorBody = ErrorApp(status = 500, message = "error message"))

        authenticationFlow.test {
            val emission = awaitItem()
            Truth.assertThat(emission).isEqualTo(ViewState.Loading)
            val emission2 = awaitItem()
            Truth.assertThat(emission2).isEqualTo(ViewState.Failure(generalError))
            awaitComplete()
        }
    }
}