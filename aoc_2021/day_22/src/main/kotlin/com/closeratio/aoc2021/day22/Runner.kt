package com.closeratio.aoc2021.day22

object Runner {

    private val processor = RegionProcessor.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        println(processor.processInstructions(Cuboid(-50..50, -50..50, -50..50)))
    }

    private fun part2() {
        println(processor.processInstructions())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}