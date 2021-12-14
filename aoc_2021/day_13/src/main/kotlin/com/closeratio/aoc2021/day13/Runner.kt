package com.closeratio.aoc2021.day13

object Runner {

    private val manual = javaClass.getResource("/input.txt")!!.readText().let(Manual.Companion::parse)

    private fun part1() {
        val result = manual.fold(manual.foldInstructions.first(), manual.dots)
        println(result.visibleDotCount)
    }

    private fun part2() {
        val result = manual.executeInstructions()
        println(result.printed)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}