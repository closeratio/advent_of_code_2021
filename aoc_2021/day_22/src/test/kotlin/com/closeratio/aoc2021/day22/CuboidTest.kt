package com.closeratio.aoc2021.day22

import com.closeratio.aoc2021.common.math.Vec3i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.jupiter.api.Test

class CuboidTest {

    @Test
    fun pointCount() {
        assertThat(
            Cuboid(
                Vec3i(10, 10, 10),
                Vec3i(12, 12, 12)
            ).pointCount,
            `is`(27)
        )

        assertThat(
            Cuboid(
                Vec3i(5, 5, 5),
                Vec3i(5, 5, 5)
            ).pointCount,
            `is`(1)
        )
    }

    @Test
    fun overlapCubeHasOverlap() {
        val cuboid1 = Cuboid(
            Vec3i(10, 10, 10),
            Vec3i(12, 12, 12)
        )

        val cuboid2 = Cuboid(
            Vec3i(11, 11, 11),
            Vec3i(13, 13, 13)
        )

        val result = cuboid1.overlapCuboid(cuboid2)!!

        assertThat(
            cuboid1.overlapCuboid(cuboid2), `is`(
                Cuboid(
                    Vec3i(11, 11, 11),
                    Vec3i(12, 12, 12)
                )
            )
        )

        assertThat(result.pointCount, `is`(8))
    }

    @Test
    fun overlapCubeNoOverlap() {
        val cuboid1 = Cuboid(
            Vec3i(10, 10, 10),
            Vec3i(12, 12, 12)
        )

        val cuboid2 = Cuboid(
            Vec3i(5, 5, 5),
            Vec3i(7, 12, 11)
        )

        val result = cuboid1.overlapCuboid(cuboid2)

        assertThat(result, `is`(nullValue()))
    }

}