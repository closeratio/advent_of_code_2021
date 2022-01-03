package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i
import java.util.*

class Burrow(
    val prawns: List<Prawn>
) {

    companion object {
        val navGrid = setOf(
            Vec2i(2, 1),
            Vec2i(2, 2),
            Vec2i(4, 1),
            Vec2i(4, 2),
            Vec2i(6, 1),
            Vec2i(6, 2),
            Vec2i(8, 1),
            Vec2i(8, 2)
        ) + IntRange(0, 10)
            .map { x -> Vec2i(x, 0) }
            .toSet()

        val invalidPositions = setOf(
            Vec2i(2, 0),
            Vec2i(4, 0),
            Vec2i(6, 0),
            Vec2i(8, 0)
        )

        fun parse(input: String): Burrow = TODO()
    }

    private val spentEnergy: Long = prawns.sumOf { it.spentEnergy }

    fun computeOrganizeEnergy(): Long {
        val exploredStates = mutableSetOf<Burrow>()
        val candidateStates = PriorityQueue(Comparator.comparingLong(Burrow::spentEnergy)).also {
            it.add(this)
        }

        while (candidateStates.isNotEmpty()) {
            val state = candidateStates.poll()
            if (state.isOrganised()) {
                return state.spentEnergy
            }

            exploredStates.add(state)

            candidateStates.addAll(computeChildStates()
                .filter { it !in exploredStates }
                .filter { it !in candidateStates }
            )
        }

        throw IllegalStateException("Unable to compute solution, explored ${exploredStates.size} states")
    }

    private fun computeChildStates(): List<Burrow> = prawns
        .flatMap { prawn ->
            prawn.generatePossiblePaths(this).map { path ->
                Burrow(
                    prawns.filter { it != prawn } + prawn.moveAlong(path)
                )
            }
        }
        .groupBy { it }
        .mapValues { (_, v) -> v.minByOrNull { burrow -> burrow.spentEnergy }!! }
        .values
        .sortedBy { it.spentEnergy }

    private fun isOrganised(): Boolean = prawns
        .all { it.position == it.primaryDestination || it.position == it.secondaryDestination }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Burrow) return false

        if (prawns != other.prawns) return false

        return true
    }

    override fun hashCode(): Int {
        return prawns.hashCode()
    }

}