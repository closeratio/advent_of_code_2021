package com.closeratio.aoc2021.day21.simple

import com.closeratio.aoc2021.day21.DiracDiceGame

class SimpleDiracDiceGame(
    player1StartingPosition: Int,
    player2StartingPosition: Int
): DiracDiceGame(player1StartingPosition, player2StartingPosition) {

    data class Result(
        val winningPlayerNumber: Int,
        val winningPlayerScore: Int,
        val losingPlayerScore: Int,
        val diceRollCount: Int
    )

    fun playGame(): Result {

        val deterministicDice = DeterministicDice()

        var player1Score = 0
        var player1Position = player1StartingPosition

        var player2Score = 0
        var player2Position = player2StartingPosition

        while (player1Score < 1000 && player2Score < 1000) {
            val player1MoveResult = movePlayer(
                listOf(deterministicDice.roll(), deterministicDice.roll(), deterministicDice.roll()).sum(),
                player1Position,
                player1Score
            )

            player1Position = player1MoveResult.newPlayerPosition
            player1Score = player1MoveResult.newPlayerScore

            if (player1Score >= 1000) {
                continue
            }

            val player2MoveResult = movePlayer(
                listOf(deterministicDice.roll(), deterministicDice.roll(), deterministicDice.roll()).sum(),
                player2Position,
                player2Score
            )

            player2Position = player2MoveResult.newPlayerPosition
            player2Score = player2MoveResult.newPlayerScore
        }

        return when (player1Score >= 1000) {
            true -> Result(
                1,
                player1Score,
                player2Score,
                deterministicDice.rollCount
            )
            false -> Result(
                2,
                player2Score,
                player1Score,
                deterministicDice.rollCount
            )
        }
    }
}