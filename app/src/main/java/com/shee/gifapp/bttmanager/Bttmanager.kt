package com.shee.gifapp.bttmanager

import com.shee.gifapp.MainActivity
import com.shee.gifapp.cachemanager.reqCache
import com.shee.gifapp.datamanager.DataManager
import com.shee.gifapp.idmanager.IDManager

class BttManager {
    private var dm = DataManager()

    public fun start(a: MainActivity) {
        dm.getNetworkData(a)
    }

    public fun next(a: MainActivity) {
        if(reqCache.dataMap.get(IDManager.currentID + 1) != null)
        {
            dm.getCacheData(a, 0)
        }
        else
        {
            dm.getNetworkData(a)
        }
    }

    public fun back(a: MainActivity) {
        if(IDManager.currentID > 0)
        {
            dm.getCacheData(a, 1)
        }
    }
}