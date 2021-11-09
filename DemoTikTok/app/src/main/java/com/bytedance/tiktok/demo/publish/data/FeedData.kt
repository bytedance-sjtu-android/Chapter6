package com.bytedance.tiktok.demo.publish.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeedData(
    @SerializedName("student_id")
    val studentID: String? = null,
    @SerializedName("user_name")
    val userName: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("image_w")
    val imageWidth: Int? = null,
    @SerializedName("image_h")
    val imageHeight: Int? = null
): Serializable