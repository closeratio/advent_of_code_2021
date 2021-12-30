package com.closeratio.aoc2021.day21

object Runner {

    private val game = DiracDiceGame.parse(javaClass.getResource("/input.txt")!!.readText())

    private fun part1() {
        val result = game.playGame(DeterministicDice())
        println(result.losingPlayerScore * result.diceRollCount)
    }

    private fun part2() {

    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }
}