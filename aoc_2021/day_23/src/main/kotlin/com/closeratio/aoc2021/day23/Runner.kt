package com.closeratio.aoc2021.day23

object Runner {

    private fun part1() {
        val burrow = Burrow.parse(javaClass.getResource("/input_simple.txt")!!.readText())
        println(burrow.computeOrganizeEnergy())
    }

    private fun part2() {
        val burrow = Burrow.parse(javaClass.getResource("/input_complex.txt")!!.readText())
        println(burrow.computeOrganizeEnergy())
    }

    @JvmStatic
    fun main(args: Array<String>) {
//        part1()
        part2()
    }

}
