package com.closeratio.aoc2021.day15

object Runner {

    private val map = javaClass.getResource("/input.txt")!!.readText().let(RiskMap.Companion::parse)

    private fun part1() {
        println(map.calculatePathRisk())
    }

    private fun part2() {

    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}