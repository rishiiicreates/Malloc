package com.passby.malloc.network

import retrofit2.Response
import retrofit2.http.GET

interface PassByApi {
    @GET("health")
    suspend fun health(): Response<Unit>
}

