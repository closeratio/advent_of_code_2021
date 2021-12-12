package com.closeratio.aoc2021.day12

object Runner {

    private val caveSystem = CaveSystem.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = caveSystem.pathCount()
        println(result)
    }

    private fun part2() {
        val result = caveSystem.pathCountComplex()
        println(result)
    }

    @JvmStatic
    fun main(array: Array<String>) {
        part1()
        part2()
    }

}