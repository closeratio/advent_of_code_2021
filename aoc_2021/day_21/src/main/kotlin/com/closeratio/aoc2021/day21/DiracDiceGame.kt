package com.closeratio.aoc2021.day21

abstract class DiracDiceGame(
    val player1StartingPosition: Int,
    val player2StartingPosition: Int
) {

    companion object {
        fun <T> parse(
            input: String,
            constructor: (p1Pos: Int, p2Pos: Int) -> T
        ): T = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map { line -> line.split(":")[1].trim().toInt() }
            .let { (player1Position, player2Position) -> constructor(player1Position, player2Position) }
    }

    protected data class MovePlayerResult(
        val newPlayerPosition: Int,
        val newPlayerScore: Int
    )

    protected fun movePlayer(
        diceRollValue: Int,
        playerPosition: Int,
        playerScore: Int
    ): MovePlayerResult {
        var newPosition = playerPosition + diceRollValue
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