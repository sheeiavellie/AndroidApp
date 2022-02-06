package com.shee.gifapp.cachemanager

import android.content.Context
import android.provider.ContactsContract
import android.widget.Toast
import com.google.gson.Gson
import com.shee.gifapp.MainActivity
import com.shee.gifapp.api.DataJson
import com.shee.gifapp.idmanager.IDManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.InputStream
import java.lang.Exception

class cacheManager {
    private  var FileName: String = "def"

    public fun setFileName(fn: String) {
        this.FileName = fn
    }

    public fun getFileName(): String {
        return this.FileName
    }

    private fun serialize (data: DataJson): String {
        val gson = Gson()
        return gson.toJson(data)
    }

    private fun deserialize (data: String): DataJson {
        val gson = Gson()
        //var ret : DataJson = gson.fromJ
    }

    public fun CreateCacheFile(a: MainActivity, data: DataJson?)
    {
        val gson = Gson()
        var jsonString: String = gson.toJson(data)

        var path = a.cacheDir
        try {
            var ff = File.createTempFile("GA", ".json", path)
            ff.writeText(jsonString)

            setFileName(ff.name)

        } catch (e: Exception) {
                Toast.makeText(a.applicationContext, "API Error", Toast.LENGTH_SHORT).show()
        }
    }
    public fun LoadFile(context: Context, id: Int): String? {

        var input: InputStream? = null
        var jsonString: String

        try {
            input = context.assets.open(reqCache.dataMap[id]!!)

            var size = input.available()
            val buffer = ByteArray(size)

            input.read(buffer)
            jsonString = String(buffer)
            return jsonString

        } catch (e: Exception){
            e.printStackTrace()

        } finally {
            if (input != null) {
                input.close()
            }
        }
        return null
    }
}