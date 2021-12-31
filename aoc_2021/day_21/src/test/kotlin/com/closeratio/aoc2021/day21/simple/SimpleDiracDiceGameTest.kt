package com.closeratio.aoc2021.day21.simple

import com.closeratio.aoc2021.day21.DiracDiceGame
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class SimpleDiracDiceGameTest {

    private val game = DiracDiceGame.parse(
        javaClass.getResource("/test_input.txt")!!.readText(),
        ::SimpleDiracDiceGame
    )

    @Test
    fun playGame() {
        val result = game.playGame()

        assertThat(result.losingPlayerScore, `is`(745))
        assertThat(result.diceRollCount, `is`(993))
        assertThat(result.losingPlayerScore * result.diceRollCount, `is`(739785))
    }


}