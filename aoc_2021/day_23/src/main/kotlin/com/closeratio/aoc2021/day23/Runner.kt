package com.closeratio.aoc2021.day23

object Runner {

    private val burrow = Burrow.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        println(burrow.computeOrganizeEnergy())
    }

    private fun part2() {

    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}
