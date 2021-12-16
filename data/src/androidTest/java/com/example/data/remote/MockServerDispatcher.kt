package com.example.data.remote

import com.example.data.BuildConfig
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 11/09/2021 created by Just clean
 */
class MockServerDispatcher {
     fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
     }

    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/pokemon/1" -> MockResponse().setResponseCode(200).setBody(getJsonContent("responseInfo.json"))
                else -> MockResponse().setResponseCode(400)
            }
        }
    }
}