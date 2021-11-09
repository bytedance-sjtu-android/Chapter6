package com.bytedance.tiktok.demo.publish.data

import java.io.Serializable

data class FeedResponse(
    val success: Boolean,
    val feeds: List<FeedData>
) : Serializable