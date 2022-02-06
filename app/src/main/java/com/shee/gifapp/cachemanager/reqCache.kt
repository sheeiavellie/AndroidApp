package com.shee.gifapp.cachemanager

import com.shee.gifapp.api.DataJson

object reqCache {
    public val dataMap = mutableMapOf<Int, String>()

    public fun add (key: Int, data: String) {
        dataMap.put(key, data)
    }
}