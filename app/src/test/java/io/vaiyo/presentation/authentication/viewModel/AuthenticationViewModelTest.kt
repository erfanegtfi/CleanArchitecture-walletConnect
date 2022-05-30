package io.vaiyo.presentation.authentication.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.vaiyo.domain.model.User
import io.vaiyo.domain.model.requests.LoginRequest
import io.vaiyo.domain.model.response.AuthenticationResponse
import io.vaiyo.domain.model.viewState.ViewState
import io.vaiyo.domain.usecase.authentication.AuthenticationUseCase
import io.vaiyo.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import androidx.lifecycle.Observer
import io.mockk.*
import io.vaiyo.domain.utils.GeneralError
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AuthenticationViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: RegisterViewModel
    private lateinit var authenticationUseCase: AuthenticationUseCase


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this).close()
        authenticationUseCase = mockk()
        viewModel = RegisterViewModel(authenticationUseCase)

    }

    private lateinit var loginRequest: LoginRequest
    private lateinit var loginOutput3: ViewState<AuthenticationResponse>
    private lateinit var loginResponse: ViewState<User>

    //    https://stackoverflow.com/questions/60713244/android-unit-testing-view-model-that-receives-flow
//    https://stackoverflow.com/questions/63339306/viewmodel-unit-testing-multiple-view-states-with-livedata-coroutines-and-mockk
    @Test
    fun `when call viewmode with valid data livedata will be success`() = runBlockingTest {
        val observer: Observer<ViewState<User>> = givenAuthenticationInputs_success()
        whenAuthenticateUser_success()
        thenViewModelSendStates_success(observer)

    }

    private fun givenAuthenticationInputs_success(): Observer<ViewState<User>> {
        loginRequest = LoginRequest("0xA2cA38CBcE6c521860D30C214f2a331F46B81cE7", "metamask")
        loginOutput3 = ViewState.Success(AuthenticationResponse(jwt = "jwt", user = User()))
        loginResponse = ViewState.Success(User())

        coEvery {
            authenticationUseCase.action(loginRequest)
        } returns flow {
            emit(ViewState.Loading) // 1. show loading
            emit(loginOutput3) // 2. show success state
        }

        val observer = mockk<Observer<ViewState<User>>>() {
            every { onChanged(any()) } just Runs
        }

        viewModel.loginLiveData.observeForever(observer)
        return observer
    }

    private fun whenAuthenticateUser_success() {
        viewModel.authenticateUser(loginRequest)
    }

    private fun thenViewModelSendStates_success(observer: Observer<ViewState<User>>) {
        verifySequence {
            observer.onChanged(ViewState.Loading) // verify loading
            observer.onChanged(loginResponse) // verify success state
        }
    }


    private lateinit var loginResponseFailure: ViewState<User>

    @Test
    fun `when call viewmode with valid data livedata will be failure`() = runBlockingTest {
        val observer: Observer<ViewState<User>> = givenAuthenticationInputs_failure()
        whenAuthenticateUser_failure()
        thenViewModelSendStates_failure(observer)

    }

    private fun givenAuthenticationInputs_failure(): Observer<ViewState<User>> {
        loginRequest = LoginRequest("0xA2cA38CBcE6c521860D30C214f2a331F46B81cE7", "metamask")
        val error = GeneralError(message = "error 1!")
        loginResponseFailure = ViewState.Failure(error)

        coEvery {
            authenticationUseCase.action(loginRequest)
        } returns flow {
            emit(ViewState.Loading) // 1. show loading
            emit(ViewState.Failure(error)) // 2. show success state
        }

        val observer = mockk<Observer<ViewState<User>>>() {
            every { onChanged(any()) } just Runs
        }

        viewModel.loginLiveData.observeForever(observer)
        return observer
    }

    private fun whenAuthenticateUser_failure() {
        viewModel.authenticateUser(loginRequest)
    }

    private fun thenViewModelSendStates_failure(observer: Observer<ViewState<User>>) {
        verifySequence {
            observer.onChanged(ViewState.Loading) // verify loading
            observer.onChanged(loginResponseFailure) // verify success state
        }
    }
}