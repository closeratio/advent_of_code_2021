package com.closeratio.aoc2021.day19

object Runner {

    private val scannerComputer = ScannerComputer.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        println(scannerComputer.processScannerData().beacons.size)
    }

    private fun part2() {
        println(scannerComputer.processScannerData().largestManhattanDistance)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}