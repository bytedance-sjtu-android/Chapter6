package com.bytedance.tiktok.demo.publish.data

import java.io.Serializable

data class PublishResponse(
    val success: Boolean,
    val result: PublishResult? = null
) : Serializable

data class PublishResult(
    val image_url: String? = null
): Serializable