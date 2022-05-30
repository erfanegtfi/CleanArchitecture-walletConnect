package io.vaiyo.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.vaiyo.data.MockWebServerBaseTest
import io.vaiyo.data.dataSource.remote.api.AuthenticationApi
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.HttpURLConnection


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AuthenticationRepositoryImpTest : MockWebServerBaseTest() {

    override fun isMockServerEnabled() = true

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var authenticationApi: AuthenticationApi

    @Before
    fun start() {
        authenticationApi = provideTestApiService(AuthenticationApi::class.java)
    }


    @Test
    fun `login api test`() = runBlocking {
        //Given
        val loginOutput = LoginRequest("0xA2cA38CBcE6c521860D30C214f2a331F46B81cE7", "metamask")

        //when call api
        mockHttpResponse("json/login_response.json", HttpURLConnection.HTTP_OK)
        val response = authenticationApi.authentication(loginOutput)

        //then jwt and user must not be null
        assertTrue(response.body()?.jwt?.isNotBlank() == true)
        assertTrue(response.body()?.user != null)
    }

}