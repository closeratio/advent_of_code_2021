package com.closeratio.aoc2021.day25

object Runner {

    private val simulation = FloorStateSimulation.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = simulation.iterationsUntilStable()
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
    }

}