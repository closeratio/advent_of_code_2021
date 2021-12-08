package com.closeratio.aoc2021.day8

object Runner {

    val observations = Observation.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = observations.sumOf { it.countOutputEasyNumbers() }
        println(result)
    }

    private fun part2() {

    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}
