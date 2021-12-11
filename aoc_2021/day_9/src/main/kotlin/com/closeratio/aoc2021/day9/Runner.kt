package com.closeratio.aoc2021.day9

object Runner {

    private val heightmap = Heightmap.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = heightmap.riskLevelSum()
        println(result)
    }

    private fun part2() {
        val result = heightmap.basinFactor()
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}