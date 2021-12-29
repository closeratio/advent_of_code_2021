package com.closeratio.aoc2021.day20

object Runner {

    private val floorMapper = FloorMapper.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = floorMapper.applyAlgorithm(2)
        println(result.litPixels.size)
    }

    private fun part2() {

    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}