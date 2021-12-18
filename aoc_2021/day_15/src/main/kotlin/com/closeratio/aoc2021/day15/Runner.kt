package com.closeratio.aoc2021.day15

import com.closeratio.aoc2021.common.math.Vec2i

object Runner {

    private val map = javaClass.getResource("/input.txt")!!.readText().let(RiskMap.Companion::parse)

    private fun part1() {
        println(map.calculatePathRisk())
    }

    private fun part2() {
        println(map.calculatePathRisk(
            Vec2i.ZERO,
            Vec2i(499, 499)
        ))
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}