package com.closeratio.aoc2021.day21

import com.closeratio.aoc2021.day21.simple.SimpleDiracDiceGame
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class DiracDiceGameTest {

    @Test
    fun parse() {
        val game = DiracDiceGame.parse(
            javaClass.getResource("/test_input.txt")!!.readText(),
            ::SimpleDiracDiceGame
        )

        MatcherAssert.assertThat(game.player1StartingPosition, Matchers.`is`(4))
        MatcherAssert.assertThat(game.player2StartingPosition, Matchers.`is`(8))
    }


}