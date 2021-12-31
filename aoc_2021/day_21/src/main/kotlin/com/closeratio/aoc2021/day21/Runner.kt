package com.closeratio.aoc2021.day21

import com.closeratio.aoc2021.day21.complex.ComplexDiracDiceGame
import com.closeratio.aoc2021.day21.simple.SimpleDiracDiceGame
import kotlin.math.max

object Runner {

    private fun part1() {
        val game = DiracDiceGame.parse(
            javaClass.getResource("/input.txt")!!.readText(),
            ::SimpleDiracDiceGame
        )
        val result = game.playGame()
        println(result.losingPlayerScore * result.diceRollCount)
    }

    private fun part2() {
        val game = DiracDiceGame.parse(
            javaClass.getResource("/input.txt")!!.readText(),
            ::ComplexDiracDiceGame
        )
        val result = game.playGame()
        println(max(
            result.player1WinningUniverseCount,
            result.player2WinningUniverseCount
        ))
    }

    @JvmStatic
    fun main(args: Array<String>) {
        part1()
        part2()
    }
}