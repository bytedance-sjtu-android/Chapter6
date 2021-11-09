package com.bytedance.tiktok.demo.publish.net

import com.bytedance.tiktok.demo.publish.data.FeedResponse
import com.bytedance.tiktok.demo.publish.data.PublishResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface INetworkApi {
    @Multipart
    @POST("video")
    fun uploadPicture(@Query("student_id") studentId: String, @Query("user_name") userName: String, @Part image: MultipartBody.Part): Call<PublishResponse>

    @GET("video")
    fun getFeeds(): Call<FeedResponse>
}