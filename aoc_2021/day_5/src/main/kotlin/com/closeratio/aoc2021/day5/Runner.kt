package com.closeratio.aoc2021.day5

object Runner {

    val analyser = LineAnalyser()

    private fun part1() {
        val result = analyser.getOverlappingPointCount(javaClass.getResource("/input.txt")!!.readText(), true)
        println(result)
    }

    private fun part2() {
        val result = analyser.getOverlappingPointCount(javaClass.getResource("/input.txt")!!.readText(), false)
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}