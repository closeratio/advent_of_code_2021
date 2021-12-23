package com.closeratio.aoc2021.day17

import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ProbeTest {

    @Test
    fun step() {
        val probe = Probe(
            Vec2i.ZERO,
            Vec2i(7, 2)
        )

        val steps = IntRange(1, 7)
            .fold(mutableListOf(probe)) { acc, _ ->
                acc.add(acc.last().step())
                acc
            }

        assertThat(steps[1], `is`(Probe(Vec2i(7, 2), Vec2i(6, 1))))
        assertThat(steps[2], `is`(Probe(Vec2i(13, 3), Vec2i(5, 0))))
        assertThat(steps[3], `is`(Probe(Vec2i(18, 3), Vec2i(4, -1))))
        assertThat(steps[4], `is`(Probe(Vec2i(22, 2), Vec2i(3, -2))))
        assertThat(steps[5], `is`(Probe(Vec2i(25, 0), Vec2i(2, -3))))
        assertThat(steps[6], `is`(Probe(Vec2i(27, -3), Vec2i(1, -4))))
        assertThat(steps[7], `is`(Probe(Vec2i(28, -7), Vec2i(0, -5))))
    }

}