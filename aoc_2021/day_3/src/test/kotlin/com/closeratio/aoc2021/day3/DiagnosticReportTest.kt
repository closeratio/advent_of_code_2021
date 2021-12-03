package com.closeratio.aoc2021.day3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.collection.IsIterableWithSize.iterableWithSize
import org.junit.jupiter.api.Test

class DiagnosticReportTest {

    private val report = javaClass
        .getResource("/test_input.txt")!!
        .readText()
        .let(::DiagnosticReport)

    @Test
    fun valuesAreParsed() {
        assertThat(report.values, iterableWithSize(12))
        assertThat(
            report.values,
            contains(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010"
            )
        )
    }

    @Test
    fun computeGammaRate() {
        val result = report.computeGammaRate()

        assertThat(result, `is`(22))
    }

    @Test
    fun computeEpsilonRate() {
        val result = report.computeEpsilonRate()

        assertThat(result, `is`(9))
    }

    @Test
    fun computePowerConsumption() {
        val result = report.computePowerConsumption()

        assertThat(result, `is`(198))
    }

    @Test
    fun computeOxygenGeneratorRating() {
        val result = report.computeOxygenGeneratorRating()

        assertThat(result, `is`(23))
    }

    @Test
    fun computeCo2ScrubberRating() {
        val result = report.computeCo2ScrubberRating()

        assertThat(result, `is`(10))
    }

    @Test
    fun computeLifeSupportRating() {
        val result = report.computeLifeSupportRating()

        assertThat(result, `is`(230))
    }

}
