package com.shee.gifapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.shee.gifapp.api.DataJson
import com.shee.gifapp.cachemanager.cacheManager
import com.shee.gifapp.cachemanager.reqCache
import com.shee.gifapp.databinding.ActivityMainBinding
import com.shee.gifapp.datamanager.DataManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.Writer
import java.lang.Exception

const val BASE_URL = "https://developerslife.ru/"

class MainActivity : AppCompatActivity() {

    public lateinit var binding: ActivityMainBinding
    private lateinit var datamanager : DataManager
    private lateinit var cachemanager : cacheManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{setContentView(it.root)}
        datamanager = DataManager()
        cachemanager = cacheManager()

        datamanager.getNetworkData(this)

        binding.nextBtt.setOnClickListener {
            datamanager.getNetworkData(this)
        }

        binding.backBtt.setOnClickListener {
            datamanager.getCacheData(this)
        }
    }
}