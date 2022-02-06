package com.shee.gifapp

import com.shee.gifapp.api.DataJson
import com.shee.gifapp.api.ResultJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiReq {
    @GET("/random?json=true")
    fun getRandom(): Call<DataJson>

    @GET("/{tom}/{page}?json=true")
    fun getLatest(@Path("page") page: Int): Call<ResultJson>

    @GET("/hot/{page}?json=true")
    fun getHot(@Path("page") page: Int): Call<ResultJson>

    @GET("/top/{page}?json=true")
    fun getTop(@Path("page") page: Int): Call<ResultJson>
}