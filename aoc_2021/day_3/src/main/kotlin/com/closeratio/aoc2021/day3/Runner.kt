package com.closeratio.aoc2021.day3

object Runner {

    private val report = DiagnosticReport(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        println(report.computePowerConsumption())
    }

    private fun part2() {
        println(report.computeLifeSupportRating())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}
