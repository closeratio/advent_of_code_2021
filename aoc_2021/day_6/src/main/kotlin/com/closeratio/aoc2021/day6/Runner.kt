package com.closeratio.aoc2021.day6

object Runner {

    private val model = LanternfishModel(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = model.iterate(80)
        println(result)
    }

    private fun part2() {
        val result = model.iterate(256)
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}
