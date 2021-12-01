package com.closeratio.aoc2021.day1

object Runner {

    private fun part1(data: List<Long>) {
        val result = SonarScanner().computeDepthCount(data)
        println(result)
    }

    private fun part2(data: List<Long>) {
        val result = SonarScanner().computeWindowedDepthCount(data)
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val data = javaClass
            .getResource("/input.txt")!!
            .readText()
            .trim()
            .split("\n")
            .map(String::trim)
            .map(String::toLong)

        part1(data)
        part2(data)
    }
}
