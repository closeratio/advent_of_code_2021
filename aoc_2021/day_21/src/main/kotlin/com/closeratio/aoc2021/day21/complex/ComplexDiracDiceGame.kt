package com.closeratio.aoc2021.day21.complex

import com.closeratio.aoc2021.day21.DiracDiceGame
import com.closeratio.aoc2021.day21.complex.GameState.PlayerNumber.PLAYER_1
import com.closeratio.aoc2021.day21.complex.GameState.PlayerNumber.PLAYER_2

class ComplexDiracDiceGame(
    player1StartingPosition: Int,
    player2StartingPosition: Int
) : DiracDiceGame(player1StartingPosition, player2StartingPosition) {

    data class Result(
        val player1WinningUniverseCount: Long,
        val player2WinningUniverseCount: Long
    )

    private val diceRollCombinations: Map<Int, Int> = IntRange(1, 3)
        .flatMap { d1 ->
            IntRange(1, 3).flatMap { d2 ->
                IntRange(1, 3).map { d3 ->
                    d1 + d2 + d3
                }
            }
        }
        .groupBy { it }
        .mapValues { (_, v) -> v.size }

    fun playGame(): Result {
        val initialState = GameState(
            0,
            player1StartingPosition,
            0,
            player2StartingPosition,
            PLAYER_1
        )

        val parentStateMap: MutableMap<GameState, MutableList<GameStateWithTransitionCount>> = linkedMapOf(
            initialState to arrayListOf()
        )

        playGame(initialState, parentStateMap)

        val universeCountCache: MutableMap<GameState, Long> = linkedMapOf(
            initialState to 1
        )

        return Result(
            parentStateMap.keys
                .filter { it.player1Score >= 21 }
                .sumOf { countUniverses(it, parentStateMap, universeCountCache) },
            parentStateMap.keys
                .filter { it.player2Score >= 21 }
                .sumOf { countUniverses(it, parentStateMap, universeCountCache) }
        )
    }

    private fun playGame(
        currentState: GameState,
        parentStateMap: MutableMap<GameState, MutableList<GameStateWithTransitionCount>>
    ): Unit = diceRollCombinations.forEach { (rollValue, permutationCount) ->
        // Move the player based on whether it's player 1 or 2's go
        val result = when (currentState.currentTurn) {
            PLAYER_1 -> movePlayer(rollValue, currentState.player1Position, currentState.player1Score)
            PLAYER_2 -> movePlayer(rollValue, currentState.player2Position, currentState.player2Score)
        }

        // Compute the new game state based on the result of moving the player
        val newState = when (currentState.currentTurn) {
            PLAYER_1 -> GameState(
                result.newPlayerScore,
                result.newPlayerPosition,
                currentState.player2Score,
                currentState.player2Position,
                PLAYER_2
            )
            PLAYER_2 -> GameState(
                currentState.player1Score,
                currentState.player1Position,
                result.newPlayerScore,
                result.newPlayerPosition,
                PLAYER_1
            )
        }

        // Check if this new state isn't one we've seen before
        val exploreChildStates = newState !in parentStateMap

        // Get the list of parent states that lead to this new state
        val stateList = parentStateMap.getOrPut(newState) { arrayListOf() }

        // Add the state which led to this new state to the map
        val currStateWithCount = GameStateWithTransitionCount(currentState, permutationCount.toLong())
        assert(stateList.find { it == currStateWithCount } == null)
        stateList.add(currStateWithCount)

        // If we've not already reached this new state and the game hasn't been won, then iterate recursively
        if (exploreChildStates && newState.player1Score < 21 && newState.player2Score < 21) {
            playGame(newState, parentStateMap)
        }
    }

    private fun countUniverses(
        gameState: GameState,
        parentStateMap: Map<GameState, List<GameStateWithTransitionCount>>,
        universeCountCache: MutableMap<GameState, Long>
    ): Long = universeCountCache
        .getOrPut(gameState) {
            parentStateMap
                .getValue(gameState)
                .sumOf {
                    it.transitionCount * countUniverses(it.state, parentStateMap, universeCountCache)
                }
        }


}