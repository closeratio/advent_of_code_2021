package com.closeratio.aoc2021.day25

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class FloorStateSimulationTest {

    private val simulation = FloorStateSimulation.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(simulation.initialState.width, `is`(10))
        assertThat(simulation.initialState.height, `is`(9))
    }

    @Test
    fun iterationsUntilStable() {
        val result = simulation.iterationsUntilStable()
        assertThat(result, `is`(58))
    }

}