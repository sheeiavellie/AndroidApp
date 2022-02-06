package com.shee.gifapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shee.gifapp.bttmanager.BttManager
import com.shee.gifapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    public lateinit var binding: ActivityMainBinding
    private lateinit var bttmanager : BttManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also{setContentView(it.root)}
        bttmanager = BttManager()

        bttmanager.start(this)

        binding.nextBtt.setOnClickListener {
            bttmanager.next(this)
        }

        binding.backBtt.setOnClickListener {
            bttmanager.back(this)
        }
    }

    override fun onStop() {
        super.onStop()
        this.cacheDir.deleteRecursively()
    }
}