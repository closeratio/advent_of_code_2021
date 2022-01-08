package com.closeratio.aoc2021.day25

import com.closeratio.aoc2021.common.math.Vec2i

abstract class Cucumber(
    val position: Vec2i,
    val constructor: (Vec2i) -> Cucumber
) {

    abstract val moveAmount: Vec2i

    private fun nextPosition(floorState: FloorState): Vec2i {
        val newPositionWithoutWrapping = position + moveAmount
        val newPosition = when {
            newPositionWithoutWrapping.x >= floorState.width -> Vec2i(0, newPositionWithoutWrapping.y)
            newPositionWithoutWrapping.y >= floorState.height -> Vec2i(newPositionWithoutWrapping.x, 0)
            else -> newPositionWithoutWrapping
        }

        return newPosition
    }

    private fun canMove(floorState: FloorState): Boolean = nextPosition(floorState) !in floorState.cucumberMap

    fun moveIfPossible(floorState: FloorState): Cucumber = when {
        canMove(floorState) -> constructor(nextPosition(floorState))
        else -> this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cucumber

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(position=$position)"
    }

}
