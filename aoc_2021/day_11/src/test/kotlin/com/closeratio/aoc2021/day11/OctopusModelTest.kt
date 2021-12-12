package com.closeratio.aoc2021.day11

import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test

class OctopusModelTest {

    private val model = OctopusModel.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        val parsed = OctopusModel.parse("""
            11111
            19991
            19191
            19991
            11111
        """.trimIndent())

        assertThat(parsed.octopusMap, aMapWithSize(25))

        assertThat(parsed.octopusMap[Vec2i(0, 0)], `is`(Octopus(Vec2i(0, 0), 1)))
        assertThat(parsed.octopusMap[Vec2i(1, 1)], `is`(Octopus(Vec2i(1, 1), 9)))
        assertThat(parsed.octopusMap[Vec2i(2, 2)], `is`(Octopus(Vec2i(2, 2), 1)))
        assertThat(parsed.octopusMap[Vec2i(3, 3)], `is`(Octopus(Vec2i(3, 3), 9)))
        assertThat(parsed.octopusMap[Vec2i(4, 4)], `is`(Octopus(Vec2i(4, 4), 1)))
    }

    @Test
    fun tickSmall() {
        val parsed = OctopusModel.parse("""
            11111
            19991
            19191
            19991
            11111
        """.trimIndent())

        parsed.tick()

        assertThat(parsed.print(), `is`("""
            34543
            40004
            50005
            40004
            34543
        """.trimIndent()))

        parsed.tick()

        assertThat(parsed.print(), `is`("""
            45654
            51115
            61116
            51115
            45654
        """.trimIndent()))

        assertThat(parsed.flashCount(), `is`(9))
    }

    @Test
    fun tick() {
        model.tick()

        assertThat(model.print(), `is`("""
            6594254334
            3856965822
            6375667284
            7252447257
            7468496589
            5278635756
            3287952832
            7993992245
            5957959665
            6394862637
        """.trimIndent()))

        model.tick()

        assertThat(model.print(), `is`("""
            8807476555
            5089087054
            8597889608
            8485769600
            8700908800
            6600088989
            6800005943
            0000007456
            9000000876
            8700006848
        """.trimIndent()))
    }

    @Test
    fun tick100Steps() {
        IntRange(1, 100).forEach {
            model.tick()
        }

        assertThat(model.print(), `is`("""
            0397666866
            0749766918
            0053976933
            0004297822
            0004229892
            0053222877
            0532222966
            9322228966
            7922286866
            6789998766
        """.trimIndent()))
        assertThat(model.flashCount(), `is`(1656))
    }

    @Test
    fun iterateUntilAllFlashed() {
        val result = model.iterateUntilAllFlash()
        assertThat(result, `is`(195))
    }

}