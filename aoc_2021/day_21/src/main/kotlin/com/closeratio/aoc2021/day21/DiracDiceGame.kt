package com.closeratio.aoc2021.day21

class DiracDiceGame(
    val player1StartingPosition: Int,
    val player2StartingPosition: Int
) {

    companion object {
        fun parse(input: String): DiracDiceGame = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map { line -> line.split(":")[1].trim().toInt() }
            .let { (player1Position, player2Position) -> DiracDiceGame(player1Position, player2Position) }
    }

    data class Result(
        val winningPlayerNumber: Int,
        val winningPlayerScore: Int,
        val losingPlayerScore: Int,
        val diceRollCount: Int
    )

    fun playGame(
        dice: Dice
    ): Result {

        var player1Score = 0
        var player1Position = player1StartingPosition

        var player2Score = 0
        var player2Position = player2StartingPosition

        while (player1Score < 1000 && player2Score < 1000) {
            val player1MoveResult = movePlayer(dice, player1Position, player1Score)

            player1Position = player1MoveResult.newPlayerPosition
            player1Score = player1MoveResult.newPlayerScore

            if (player1Score >= 1000) {
                continue
            }

            val player2MoveResult = movePlayer(dice, player2Position, player2Score)

            player2Position = player2MoveResult.newPlayerPosition
            player2Score = player2MoveResult.newPlayerScore
        }

        return when (player1Score >= 1000) {
            true -> Result(
                1,
                player1Score,
                player2Score,
                dice.rollCount
            )
            false -> Result(
                2,
                player2Score,
                player1Score,
                dice.rollCount
            )
        }
    }

    private data class MovePlayerResult(
        val newPlayerPosition: Int,
        val newPlayerScore: Int
    )

    private fun movePlayer(
        dice: Dice,
        playerPosition: Int,
        playerScore: Int
    ): MovePlayerResult {
        val playerMoveAmount = listOf(dice.roll(), dice.roll(), dice.roll())
        var newPosition = playerPosition + playerMoveAmount.sum()
        if (newPosition > 10) {
            if (newPosition % 10 == 0) {
                newPosition = 10
            } else {
                newPosition %= 10
            }
        }

        return MovePlayerResult(
            newPosition,
            playerScore + newPosition
        )
    }
}