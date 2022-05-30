package io.vaiyo.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.vaiyo.data.MockWebServerBaseTest
import io.vaiyo.data.dataSource.remote.api.RoomApi
import io.vaiyo.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RoomRepositoryImpTest : MockWebServerBaseTest() {
    override fun isMockServerEnabled() = true

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var roomApi: RoomApi

    @Before
    fun start() {
        roomApi = provideTestApiService(RoomApi::class.java)
    }


    @Test
    fun `room list api test`() = runBlocking {
        //Given

        //when call api
        mockHttpResponse("json/room_list_response.json", HttpURLConnection.HTTP_OK)
        val response = roomApi.list()

        //then jwt and user must not be null
        assertTrue(response.body()?.rooms?.isNotEmpty() == true)
        assertTrue(response.body()?.rooms?.get(0)?.name?.isNotBlank() == true)
        assertTrue(response.body()?.rooms?.get(1)?.participates?.isNotEmpty() == true)
    }

}