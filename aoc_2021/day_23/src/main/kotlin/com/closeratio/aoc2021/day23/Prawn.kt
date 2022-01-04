package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i
import java.util.*

abstract class Prawn(
    val position: Vec2i,
    val spentEnergy: Long,
    private val subclassConstructor: (position: Vec2i, spentEnergy: Long) -> Prawn
) {

    abstract fun moveUnitCost(): Long

    abstract val destinationColumnX: Int

    private fun pathCost(path: Path): Long = (path.path.size - 1) * moveUnitCost()

    fun moveAlong(path: Path): Prawn = subclassConstructor(
        path.path.last(),
        spentEnergy + pathCost(path)
    )

    fun generatePossiblePaths(
        burrow: Burrow
    ): List<Path> {
        // Check if we're already in the primary destination. If we are, then we don't need to move
        if (!isInHallway() && position.x == destinationColumnX) {
            val inFinalPosition = allPrawnsBelowOrganised(position, burrow)

            if (inFinalPosition) {
                return emptyList()
            }
        }

        // Compute all the available paths from this location
        val reachablePositions = computeReachablePositions(burrow)

        // If we can reach our primary or secondary destination, then only consider those 2 paths
        val primaryPath: Path? = reachablePositions
            .filter { it.finish.x == destinationColumnX }
            .maxByOrNull { it.finish.y }
            ?.let { path ->
                if (allPrawnsBelowOrganised(path.finish, burrow)) {
                    path
                } else {
                    null
                }
            }

        if (primaryPath != null) {
            return listOf(primaryPath)
        }

        if (!isInHallway()) {
            return reachablePositions.filter { it.finish.y == 0 }
        }

        return emptyList()
    }

    private fun computeReachablePositions(burrow: Burrow): List<Path> {
        val occupiedPositions = burrow.prawnPositions

        val prevMap = HashMap<Vec2i, Vec2i>(64)
        val exploredPositions = HashSet<Vec2i>(64)
        val candidates = LinkedList<Vec2i>()
        candidates.add(position)

        while (candidates.isNotEmpty()) {
            val candidate = candidates.poll()
            exploredPositions.add(candidate)

            val adjacentCandidates = burrow
                .adjacentPositionMap
                .getValue(candidate)
                .filter { it !in occupiedPositions }
                .filter { it !in exploredPositions }
                .filter { it !in prevMap }

            adjacentCandidates.forEach { prevMap[it] = candidate }

            candidates.addAll(adjacentCandidates)
        }

        return exploredPositions
            .filter { it != position }
            .filter { it !in Burrow.invalidPositions }
            .map { destination ->
                val path = LinkedList<Vec2i>()
                path.add(destination)

                while (path.last() != position) {
                    path.add(prevMap.getValue(path.last()))
                }

                Path(path.reversed())
            }
    }

    private fun isInHallway(): Boolean = position.y == 0

    private fun allPrawnsBelowOrganised(
        position: Vec2i,
        burrow: Burrow
    ): Boolean {
        if (position.y == burrow.sideRoomSize) {
            return true
        }

        return IntRange(position.y + 1, burrow.sideRoomSize)
            .map { y -> Vec2i(position.x, y) }
            .map { burrow.prawnPositions[it] }
            .all { it?.isOrganised() ?: false }
    }

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

    fun isOrganised(): Boolean = position.x == destinationColumnX

}

