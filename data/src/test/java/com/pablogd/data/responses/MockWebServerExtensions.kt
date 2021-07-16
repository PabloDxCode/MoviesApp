package com.pablogd.data.responses

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

internal fun MockWebServer.enqueueResponse(responseCode: Int, code: Int) {

    val source = responses[responseCode]
    enqueue(
        MockResponse()
            .setResponseCode(code)
            .setBody(source ?: "{}")
    )
}
