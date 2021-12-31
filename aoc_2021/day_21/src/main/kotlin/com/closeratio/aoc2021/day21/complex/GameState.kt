package com.closeratio.aoc2021.day21.complex

data class GameState(
    val player1Score: Int,
    val player1Position: Int,
    val player2Score: Int,
    val player2Position: Int,
    val currentTurn: PlayerNumber
) {

    enum class PlayerNumber {
        PLAYER_1,
        PLAYER_2
    }

}