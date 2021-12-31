package com.closeratio.aoc2021.day21.complex

class GameStateWithTransitionCount(
    val state: GameState,
    val transitionCount: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameStateWithTransitionCount) return false

        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        return state.hashCode()
    }

    override fun toString(): String {
        return "GameStateWithTransitionCount(state=$state, transitionCount=$transitionCount)"
    }
}