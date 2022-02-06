package com.shee.gifapp.idmanager

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class IDManagerTest : TestCase() {

    private var idManager = IDManager

    @Test
    fun testInit() {
        assertEquals(-1, idManager.currentID)
    }

    @Test
    fun testUpdateOnZeroState() {
        idManager.updateID(0)
        assertEquals(0, idManager.currentID)
    }
}