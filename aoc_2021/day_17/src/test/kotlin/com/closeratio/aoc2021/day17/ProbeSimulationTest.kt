package com.closeratio.aoc2021.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ProbeSimulationTest {

    private val probeSimulation = ProbeSimulation.parse("target area: x=20..30, y=-10..-5")

    @Test
    fun parse() {
        assertThat(
            probeSimulation.targetArea, `is`(
                TargetArea(
                    20..30,
                    -10..-5
                )
            )
        )
    }

    @Test
    fun calculateMaxY() {
        val result = probeSimulation.calculateMaxY()
        assertThat(result, `is`(45))
    }

    @Test
    fun calculateVelocityPermutationCount() {
        val result = probeSimulation.calculateVelocityPermutationCount()
        assertThat(result, `is`(112))
    }

}