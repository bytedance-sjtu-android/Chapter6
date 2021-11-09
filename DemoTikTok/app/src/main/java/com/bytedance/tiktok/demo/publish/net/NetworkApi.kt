package com.bytedance.tiktok.demo.publish.net

import com.bytedance.tiktok.demo.publish.data.FeedResponse
import com.bytedance.tiktok.demo.publish.data.PublishResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException


object NetworkApi {

    private var API_BASE_URL = "https://cloudapi.bytedance.net/faas/services/tttyvl/invoke/"

    private val httpClient: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
    }

    private val builder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )

    }
    private val retrofit: Retrofit by lazy {
        builder
            .client(
                httpClient.build()
            )
            .build()
    }

    private val api: INetworkApi by lazy {
        retrofit.create(INetworkApi::class.java)
    }

    private fun getMultipartFromAsset(videoUrl: String): MultipartBody.Part {
        val partKey = "cover_image"
        val output = ByteArrayOutputStream()
        try {
            val inputStream = FileInputStream(File(videoUrl))
            val buffer = ByteArray(4096)
            var n = 0
            while (-1 != inputStream.read(buffer).also { n = it }) {
                output.write(buffer, 0, n)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("multipart/form_data"), output.toByteArray())
        return MultipartBody.Part.createFormData(partKey, videoUrl, requestBody)
    }

    fun postVideo(name: String, file: File, cb: Callback<PublishResponse>) {
        val call = api.uploadPicture("ci", name, getMultipartFromAsset(file.absolutePath))
        call.enqueue(cb)
    }

    fun getAllFeeds(cb: Callback<FeedResponse>) {
        val call = api.getFeeds()
        call.enqueue(cb)
    }
}

