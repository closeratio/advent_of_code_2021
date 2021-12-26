package com.closeratio.aoc2021.day18

object Runner {

    private fun part1() {
        val homework = Homework.parse(javaClass.getResource("/input.txt")!!.readText())
        val result = homework.sumMagnitude()
        println(result.magnitude())
    }

    private fun part2() {

    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}