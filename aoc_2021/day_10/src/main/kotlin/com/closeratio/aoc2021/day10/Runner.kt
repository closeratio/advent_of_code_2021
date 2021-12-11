package com.closeratio.aoc2021.day10

object Runner {

    private val chunkParser = ChunkParser(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = chunkParser.syntaxErrorScore()
        println(result)
    }

    private fun part2() {
        val result = chunkParser.middleCompletionScore()
        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}