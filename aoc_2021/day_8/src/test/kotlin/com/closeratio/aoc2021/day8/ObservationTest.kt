package com.closeratio.aoc2021.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
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
                    listOf(
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
                    listOf(
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

}
