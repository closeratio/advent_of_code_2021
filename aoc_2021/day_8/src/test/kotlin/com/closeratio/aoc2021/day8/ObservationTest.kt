package com.closeratio.aoc2021.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.junit.jupiter.api.Test

class ObservationTest {

    private val observation = Observation.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(observation, hasSize(10))

        val result = observation.first()

        assertThat(result.patterns, hasSize(10))
        assertThat(
            result.patterns.first(), `is`(
                SignalPattern(
                    setOf(
                        Signal('b'),
                        Signal('e')
                    )
                )
            )
        )

        assertThat(result.outputValues, hasSize(4))
        assertThat(
            result.outputValues.first(), `is`(
                SignalPattern(
                    setOf(
                        Signal('f'),
                        Signal('d'),
                        Signal('g'),
                        Signal('a'),
                        Signal('c'),
                        Signal('b'),
                        Signal('e')
                    )
                )
            )
        )
    }

    @Test
    fun countOutputEasyNumbers() {
        assertThat(observation.sumOf { it.countOutputEasyNumbers() }, `is`(26))
    }

    @Test
    fun computeMappings() {
        val result = Observation
            .parse("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
            .first()
            .computeMappings()

        assertThat(result, hasSize(10))
        assertThat(result, containsInAnyOrder(
            *listOf(
                "acedgfb" to 8,
                "cdfbe" to 5,
                "gcdfa" to 2,
                "fbcad" to 3,
                "dab" to 7,
                "cefabd" to 9,
                "cdfgeb" to 6,
                "eafb" to 4,
                "cagedb" to 0,
                "ab" to 1
            )
                .map { (l, r) -> SignalPattern(l.map(::Signal).toSet()) to Digit(r) }
                .map { (l, r) -> SignalPatternDigitMapping(l, r) }
                .toTypedArray()
        ))
    }

    @Test
    fun computeOutputValue() {
        val result = Observation
            .parse("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")
            .first()
            .computeOutputValue()

        assertThat(result, `is`(5353))
    }

    @Test
    fun computeSumOfOutputs() {
        val result = observation.sumOf(Observation::computeOutputValue)

        assertThat(result, `is`(61229))
    }

}
