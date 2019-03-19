package com.poc.fioalpha.a100topgames.presentation.view

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class EndLessStrategiesTest {

    @Test
    fun `do update item` () {
        val notUpdate = EndLessStrategies.create().isUpdateData(10,  1)
        Assert.assertFalse(notUpdate)

        val doUpdate = EndLessStrategies.create().isUpdateData(10,10)
        Assert.assertTrue(doUpdate)

        val notUpdate1 = EndLessStrategies.create().isUpdateData(12,  10)
        Assert.assertFalse(notUpdate1)

    }

}