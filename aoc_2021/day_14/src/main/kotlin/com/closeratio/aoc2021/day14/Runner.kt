package com.closeratio.aoc2021.day14

object Runner {

    private val instructions = Instructions.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = instructions.quantityDifference(10)
        println(result)
    }

    private fun part2() {
        val result = instructions.quantityDifference(40)
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}
