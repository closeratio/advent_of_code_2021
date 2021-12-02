package com.closeratio.aoc2021.day2

import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class SimpleSubmarineTest {

    private val submarine = SimpleSubmarine(Vec2i.ZERO)

    @Test
    fun directions() {
        val result = submarine
            .forward(5)
            .down(5)
            .forward(8)
            .up(3)
            .down(8)
            .forward(2)

        assertThat(result.calculatePositionDigest(), `is`(150))
    }

}
