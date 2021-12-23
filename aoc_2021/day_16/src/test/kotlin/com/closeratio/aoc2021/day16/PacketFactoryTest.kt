package com.closeratio.aoc2021.day16

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PacketFactoryTest {

    @Test
    fun getTypeId() {
        val typeId = PacketFactory.getTypeId(
            CharBuffer(
                listOf(
                    '1', '1', '0', '1', '0', '0'
                )
            )
        )

        assertThat(typeId, `is`(4))
    }

}