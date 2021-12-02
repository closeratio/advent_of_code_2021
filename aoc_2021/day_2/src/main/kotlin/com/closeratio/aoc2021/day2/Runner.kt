package com.closeratio.aoc2021.day2

import com.closeratio.aoc2021.common.math.Vec2i

object Runner {

    private fun part1() {
        val submarine = RouteParser()
            .routeSubmarine(
                SimpleSubmarine(Vec2i.ZERO),
                javaClass.getResource("/input.txt")!!.readText()
            )

        println(submarine.calculatePositionDigest())
    }

    private fun part2() {
        val submarine = RouteParser()
            .routeSubmarine(
                ComplexSubmarine(Vec2i.ZERO, 0),
                javaClass.getResource("/input.txt")!!.readText()
            )

        println(submarine.calculatePositionDigest())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}
