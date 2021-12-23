package com.closeratio.aoc2021.day16

object Runner {

    private val decoder = PacketDecoder()
    private val packet = decoder.parseTransmission(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = packet.versionSum()
        println(result)
    }

    private fun part2() {
        val result = packet.computeValue()
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}