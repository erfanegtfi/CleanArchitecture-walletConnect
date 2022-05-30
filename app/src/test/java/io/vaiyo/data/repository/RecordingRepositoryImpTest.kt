package io.vaiyo.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.vaiyo.data.MockWebServerBaseTest
import io.vaiyo.data.dataSource.remote.api.RecordingApi
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
class RecordingRepositoryImpTest : MockWebServerBaseTest() {

    override fun isMockServerEnabled() = true

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var recordingApi: RecordingApi

    @Before
    fun start() {
        recordingApi = provideTestApiService(RecordingApi::class.java)
    }


    @Test
    fun `login api test`() = runBlocking {
        //Given

        //when call api
        mockHttpResponse("json/single_room_records_list_response.json", HttpURLConnection.HTTP_OK)
        val response = recordingApi.getRecording("lhfrzveqyctjn2kikvqvmql9lgsxrvag4g1atijd")

        //then jwt and user must not be null
        assertTrue(response.body()?.recordings?.recordings?.isNotEmpty() == true)
        assertTrue(response.body()?.recordings?.recordings?.get(0)?.name?.isNotBlank() == true)
        assertTrue(response.body()?.recordings?.recordings?.get(0)?.playback?.format?.url?.isNotBlank() == true)
    }

}