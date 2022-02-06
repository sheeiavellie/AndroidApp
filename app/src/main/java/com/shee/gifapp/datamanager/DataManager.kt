package com.shee.gifapp.datamanager

import android.content.ContentValues.TAG
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shee.gifapp.ApiReq
import com.shee.gifapp.BASE_URL
import com.shee.gifapp.MainActivity
import com.shee.gifapp.R
import com.shee.gifapp.cachemanager.cacheManager
import com.shee.gifapp.cachemanager.reqCache
import com.shee.gifapp.idmanager.IDManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class DataManager {

    private var cm = cacheManager()
    public fun getStartData(a: MainActivity) {

    }
    public fun getNetworkData(a: MainActivity) {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiReq::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getRandom().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d(TAG, data.description)

                    withContext(Dispatchers.Main) {
                        a.binding.mainImageProgressbar.visibility = View.VISIBLE
                        PrintData(a,"https" + data.gifURL.drop(4), data.description)

                        cm.CreateCacheFile(a, data)
                        reqCache.add(IDManager.currentID, cm.getFileName())
                        IDManager.updateID(0)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Toast.makeText(a.applicationContext, "API Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    public fun getCacheData(a: MainActivity) {



    }

    public fun PrintData(a:MainActivity, url: String, des: String) {
        Glide.with(a.applicationContext)
            .load(url)
            .centerCrop()
            .error(R.drawable.ic_error_pepe)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    a.binding.mainImageProgressbar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    a.binding.mainImageProgressbar.visibility = View.GONE
                    return false
                }
            })
            .into(a.binding.mainImage)
        a.binding.mainImageText.text = des
    }
}