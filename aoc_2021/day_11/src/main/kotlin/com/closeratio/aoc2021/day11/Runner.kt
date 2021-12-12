package com.closeratio.aoc2021.day11

object Runner {


    private fun part1() {
        val model = javaClass.getResource("/input.txt")!!.readText().let(OctopusModel.Companion::parse)
        repeat(100) { model.tick() }
        println(model.flashCount())
    }

    private fun part2() {
        val model = javaClass.getResource("/input.txt")!!.readText().let(OctopusModel.Companion::parse)
        val result = model.iterateUntilAllFlash()
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}