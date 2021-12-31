package com.closeratio.aoc2021.day21.complex

import com.closeratio.aoc2021.day21.DiracDiceGame
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ComplexDiracDiceGameTest {

    private val game = DiracDiceGame.parse(
        javaClass.getResource("/test_input.txt")!!.readText(),
        ::ComplexDiracDiceGame
    )

    @Test
    fun playGame() {
        val result = game.playGame()

        assertThat(result.player1WinningUniverseCount, `is`(444356092776315))
        assertThat(result.player2WinningUniverseCount, `is`(341960390180808))
    }

}