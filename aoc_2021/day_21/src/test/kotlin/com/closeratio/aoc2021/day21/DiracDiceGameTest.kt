package com.closeratio.aoc2021.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class DiracDiceGameTest {

    private val game = DiracDiceGame.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(game.player1StartingPosition, `is`(4))
        assertThat(game.player2StartingPosition, `is`(8))
    }

    @Test
    fun playGame() {
        val result = game.playGame(DeterministicDice())

        assertThat(result.losingPlayerScore, `is`(745))
        assertThat(result.diceRollCount, `is`(993))
        assertThat(result.losingPlayerScore * result.diceRollCount, `is`(739785))
    }


}