package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i
import java.util.*

abstract class Prawn(
    val position: Vec2i,
    val spentEnergy: Long,
    private val subclassConstructor: (position: Vec2i, spentEnergy: Long) -> Prawn
) {

    abstract fun moveUnitCost(): Long

    abstract val primaryDestination: Vec2i
    abstract val secondaryDestination: Vec2i

    private fun pathCost(path: Path): Long = (path.path.size - 1) * moveUnitCost()

    fun moveAlong(path: Path): Prawn = subclassConstructor(
        path.path.last(),
        spentEnergy + pathCost(path)
    )

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
        val reachablePositions = computeReachablePositions(burrow)
            .filter { it.finish !in Burrow.invalidPositions }

        // If we can reach our primary or secondary destination, then only consider those 2 paths
        val primaryPath = reachablePositions.find { it.finish == primaryDestination }
        if (primaryPath != null) {
            return listOf(primaryPath)
        }

        val friendPrawnInPrimaryDestination = friendPrawn.position == primaryDestination
        val secondaryPath = reachablePositions.find { it.finish == secondaryDestination }
        if (friendPrawnInPrimaryDestination && secondaryPath != null) {
            return listOf(secondaryPath)
        }

        if (!isInHallway()) {
            return reachablePositions.filter { it.finish.y == 0 }
        }

        return emptyList()
    }

    private fun computeReachablePositions(burrow: Burrow): List<Path> {
        val occupiedPositions = burrow.prawnPositions

        val prevMap = mutableMapOf<Vec2i, Vec2i>()
        val exploredPositions = mutableSetOf<Vec2i>()
        val candidates = LinkedList(listOf(position))

        while (candidates.isNotEmpty()) {
            val candidate = candidates.poll()
            exploredPositions.add(candidate)

            val adjacentCandidates = candidate
                .adjacentAsList()
                .filter { it in Burrow.navGrid }
                .filter { it !in occupiedPositions }
                .filter { it !in exploredPositions }
                .filter { it !in prevMap }

            adjacentCandidates.forEach { prevMap[it] = candidate }

            candidates.addAll(adjacentCandidates)
        }

        return exploredPositions
            .filter { it != position }
            .map { destination ->
                val path = mutableListOf(destination)

                while (path.last() != position) {
                    path.add(prevMap.getValue(path.last()))
                }

                Path.from(path.reversed())
            }
    }

    private fun isInHallway(): Boolean = position.y == 0

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

