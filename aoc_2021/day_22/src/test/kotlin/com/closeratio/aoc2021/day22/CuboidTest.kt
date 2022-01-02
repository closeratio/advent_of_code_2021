package com.closeratio.aoc2021.day22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test

class CuboidTest {

    @Test
    fun pointCount() {
        assertThat(
            Cuboid(
                10..12,
                10..12,
                10..12
            ).pointCount,
            `is`(27)
        )

        assertThat(
            Cuboid(
                5..5,
                5..5,
                5..5
            ).pointCount,
            `is`(1)
        )
    }

    @Test
    fun overlapCubeHasOverlap() {
        val cuboid1 = Cuboid(
            10..12,
            10..12,
            10..12
        )

        val cuboid2 = Cuboid(
            11..13,
            11..13,
            11..13
        )

        val result = cuboid1.overlapCuboid(cuboid2)!!

        assertThat(
            cuboid1.overlapCuboid(cuboid2), `is`(
                Cuboid(
                    11..12,
                    11..12,
                    11..12
                )
            )
        )

        assertThat(result.pointCount, `is`(8))
    }

    @Test
    fun overlapCubeNoOverlap() {
        val cuboid1 = Cuboid(
            10..12,
            10..12,
            10..12
        )

        val cuboid2 = Cuboid(
            5..7,
            5..12,
            5..11
        )

        val result = cuboid1.overlapCuboid(cuboid2)

        assertThat(result, `is`(nullValue()))
    }

}