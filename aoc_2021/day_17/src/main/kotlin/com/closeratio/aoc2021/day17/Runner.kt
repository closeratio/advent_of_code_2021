package com.closeratio.aoc2021.day17

object Runner {

    private val probeSimulation = ProbeSimulation.parse("target area: x=179..201, y=-109..-63")

    private fun part1() {
        val result = probeSimulation.calculateMaxY()
        println(result)
    }

    private fun part2() {
        val result = probeSimulation.calculateVelocityPermutationCount()
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}