package com.shee.gifapp.cachemanager

import android.widget.Toast
import com.google.gson.Gson
import com.shee.gifapp.MainActivity
import com.shee.gifapp.api.DataJson
import java.io.File
import java.lang.Exception

class CacheManager {
    private  var fileName: String = "def"

    public fun setFileName(fn: String) {
        this.fileName = fn
    }

    public fun getFileName(): String {
        return this.fileName
    }



    public fun createCacheFile(a: MainActivity, data: DataJson)
    {
        val gson = Gson()
        var jsonString: String = gson.toJson(data)

        var path = a.cacheDir

        try {
            var ff = File.createTempFile("gatemp", ".json", path)
            ff.writeText(jsonString)

            setFileName(ff.name)

        } catch (e: Exception) {
            Toast.makeText(a.applicationContext, "Create cache Error", Toast.LENGTH_SHORT).show()
        }
    }
    public fun loadFile(a: MainActivity, fn: String?): DataJson? {
        val gson = Gson()

        var path = a.cacheDir.absolutePath + "/" + fn;

        try {
            val jsonString: String = File(path).readText(Charsets.UTF_8)
            return gson.fromJson(jsonString, DataJson::class.java)
        } catch (e: Exception) {
            System.out.println("Error" + e.toString())
            System.out.println(path)
            Toast.makeText(a.applicationContext, "Load Cache Error", Toast.LENGTH_SHORT).show()
        }
        return null
    }
}