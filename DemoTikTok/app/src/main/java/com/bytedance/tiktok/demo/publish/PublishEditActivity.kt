package com.bytedance.tiktok.demo.publish

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.tiktok.demo.R
import com.bytedance.tiktok.demo.imageselector.ImageSelectActivity
import com.bytedance.tiktok.demo.publish.data.FeedResponse
import com.bytedance.tiktok.demo.publish.data.PublishResponse
import com.bytedance.tiktok.demo.publish.net.NetworkApi
import kotlinx.android.synthetic.main.activity_publish.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PublishEditActivity : AppCompatActivity() {

    private var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)
        btn_add.setOnClickListener {
            choosePicture()
        }
        btn_close.setOnClickListener {
            finish()
        }
        btn_upload.setOnClickListener {
            file ?: return@setOnClickListener
            NetworkApi.postVideo(
                edit_text.text?.toString() ?: "",
                file!!,
                object : Callback<PublishResponse> {
                    override fun onFailure(call: Call<PublishResponse>, t: Throwable) {
                        //Network Fail
                    }

                    override fun onResponse(
                        call: Call<PublishResponse>,
                        response: Response<PublishResponse>
                    ) {
                        if (response.isSuccessful && response.body()?.success == true) {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }
                })
        }
    }

    private fun choosePicture() {
        var intent = Intent(this, ImageSelectActivity::class.java)
        startActivityForResult(intent, 0x0001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0x0001) {
            val filePath = data?.getStringExtra("path")
            val bitmap = BitmapFactory.decodeFile(filePath)
            if (bitmap != null) {
                file = File(filePath)
                image_view.setImageBitmap(bitmap)
            }
        }
    }
}