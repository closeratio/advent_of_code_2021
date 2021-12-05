package com.closeratio.aoc2021.day5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class LineAnalyserTest {

    private val analyser = LineAnalyser()

    @Test
    fun getOverlappingPointCountNoDiagonals() {
        val result = analyser.getOverlappingPointCount(javaClass.getResource("/test_input.txt")!!.readText(), true)

        assertThat(result, `is`(5))
    }

    @Test
    fun getOverlappingPointCountWithDiagonals() {
        val result = analyser.getOverlappingPointCount(javaClass.getResource("/test_input.txt")!!.readText(), true)

        assertThat(result, `is`(12))
    }

}