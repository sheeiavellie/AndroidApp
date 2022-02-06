package com.shee.gifapp.idmanager

object IDManager {
    public var currentID: Int = 0

    public fun updateID(state: Int) {
        when(state) {
            0 -> currentID += 1
            1 -> currentID -= 1
        }
}
}