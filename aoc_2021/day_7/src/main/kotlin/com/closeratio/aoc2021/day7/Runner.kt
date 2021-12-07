package com.closeratio.aoc2021.day7

object Runner {

    private val model = CrabModel(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = model.calculateFuelForAlignmentSimple()
        println(result)
    }

    private fun part2() {
        val result = model.calculateFuelForAlignmentComplex()
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}
