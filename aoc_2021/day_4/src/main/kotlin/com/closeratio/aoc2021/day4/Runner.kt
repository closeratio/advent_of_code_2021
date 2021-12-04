package com.closeratio.aoc2021.day4

object Runner {

    private val game = BingoGame(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val score = game.calculateWinningScore()
        println(score)
    }

    private fun part2() {
        val score = game.calculateLastWinningScore()
        println(score)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }

}