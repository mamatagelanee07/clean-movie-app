package com.andigeeky.movies.remote.details.source

import com.andigeeky.movies.remote.movies.MoviesService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class MovieDetailsRemoteTest {

    private lateinit var mockWebServer : MockWebServer
    private lateinit var service : MoviesService
    private val id = 1

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MoviesService::class.java)
    }

    @Test
    fun testGetData(){
        enqueueResponse("movie_detail.json")
        val testSubscriber = service.getMovieDetails(id)?.test()
        testSubscriber?.assertValue { it.productionCompanies?.size == 7 }
    }

    @Test
    fun testGetDataCompletes(){
        enqueueResponse("movie_detail.json")
        val testSubscriber = service.getMovieDetails(id)?.test()
        testSubscriber?.assertComplete()
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("api-response/$fileName")
        val source = inputStream
            ?.source()?.buffer()

        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        source?.let {
            mockWebServer.enqueue(
                mockResponse
                    .setBody(it.readString(Charsets.UTF_8))
            )
        }
    }
}
