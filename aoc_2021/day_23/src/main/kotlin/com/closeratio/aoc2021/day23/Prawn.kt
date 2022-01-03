package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i

abstract class Prawn(
    val position: Vec2i,
    val spentEnergy: Long,
    private val subclassConstructor: (position: Vec2i, spentEnergy: Long) -> Prawn
) {

    abstract fun moveUnitCost(): Long

    abstract val primaryDestination: Vec2i
    abstract val secondaryDestination: Vec2i

    fun pathCost(path: Path): Long = path.path.size * moveUnitCost()

    fun moveAlong(path: Path): Prawn {
        // Check that the list contains adjacent positions
        (listOf(position) + path.path).zipWithNext().forEach { (first, second) ->
            if (first.minus(second).manhattanDistance() != 1) {
                throw IllegalArgumentException("Bad path: $path")
            }
        }

        return subclassConstructor(
            path.path.last(),
            pathCost(path)
        )
    }

    fun generatePossiblePaths(
        burrow: Burrow
    ): List<Path> {
        // Check if we're already in the primary destination. If we are, then we don't need to move
        if (position == primaryDestination) {
            return emptyList()
        }

        // Check if we're already in the secondary position and another prawn of the same type is in the primary
        // position. If it is, then we don't need to move
        val friendPrawn = burrow.prawns.find { it.javaClass == javaClass && it.position != position }!!
        if (position == secondaryDestination && friendPrawn.position == primaryDestination) {
            return emptyList()
        }

        // Compute all the available paths from this location
        val availablePaths = computeAvailablePaths()

        TODO()
    }

    private fun computeAvailablePaths(): List<Path> = emptyList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Prawn) return false
        if (javaClass != other.javaClass) return false

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(position=$position, spentEnergy=$spentEnergy)"
    }

}

