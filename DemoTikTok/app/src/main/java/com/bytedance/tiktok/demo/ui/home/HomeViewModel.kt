package com.bytedance.tiktok.demo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bytedance.tiktok.demo.publish.data.FeedResponse
import com.bytedance.tiktok.demo.publish.net.NetworkApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _feeds = MutableLiveData<FeedResponse?>()
    val feed: LiveData<FeedResponse?> = _feeds

    fun refreshHomeData() {
        _feeds.postValue(null)
        NetworkApi.getAllFeeds(object : Callback<FeedResponse> {
            override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                //Network Fail
                Log.d(TAG, "getAllFeeds onFailure: $t")
            }

            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
                if (response.isSuccessful) {
                    val feedResponse: FeedResponse? = response.body()
                    feedResponse?.let {
                        _feeds.postValue(it)
                    } ?: let {
                        Log.d(TAG, "getAllFeeds onResponse empty")
                    }
                } else {
                    Log.d(TAG, "getAllFeeds onResponse fail: ${response.errorBody()}")
                }
            }
        })
    }
}