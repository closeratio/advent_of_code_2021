package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i
import java.util.*

class Burrow(
    private val parentState: Burrow?,
    val prawns: List<Prawn>,
    val navGrid: Set<Vec2i>,
    val adjacentPositionMap: Map<Vec2i, List<Vec2i>>
) {

    companion object {
        val invalidPositions = setOf(
            Vec2i(2, 0),
            Vec2i(4, 0),
            Vec2i(6, 0),
            Vec2i(8, 0)
        )

        fun parse(input: String): Burrow = input
            .trim()
            .split("\n")
            .drop(1)
            .dropLast(1)
            .map { it.drop(1).dropLast(1) }
            .flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, char ->
                    when (char) {
                        'A' -> AmberPrawn(Vec2i(x, y), 0)
                        'B' -> BronzePrawn(Vec2i(x, y), 0)
                        'C' -> CopperPrawn(Vec2i(x, y), 0)
                        'D' -> DesertPrawn(Vec2i(x, y), 0)
                        else -> null
                    }
                }
            }
            .let { prawns ->
                val navGrid = IntRange(0, 10)
                    .map { x -> Vec2i(x, 0) }
                    .toSet() + prawns.map { it.position }

                val adjacentPositionMap = navGrid
                    .associateWith { position -> position.adjacentAsList().filter { it in navGrid } }

                Burrow(null, prawns, navGrid, adjacentPositionMap)
            }
    }

    private val spentEnergy by lazy {
        prawns.sumOf { it.spentEnergy }
    }

    val prawnPositions by lazy {
        prawns.associateBy { it.position }
    }

    val sideRoomSize by lazy {
        navGrid.maxOf(Vec2i::y)
    }

    fun computeOrganizeEnergy(): Long {
        val exploredStates = mutableSetOf<Burrow>()
        val candidateStates = PriorityQueue(Comparator.comparingLong(Burrow::spentEnergy)).also {
            it.add(this)
        }
        val candidateStateSet = mutableSetOf(this)

        while (candidateStates.isNotEmpty()) {
            val state = candidateStates.poll()
            candidateStateSet.remove(state)

            if (state.isOrganised()) {
                val stateList = mutableListOf(state)
                while (stateList.last().parentState != null) {
                    stateList.add(stateList.last().parentState!!)
                }

                return state.spentEnergy
            }

            exploredStates.add(state)

            state.computeChildStates()
                .filter { it !in exploredStates }
                .filter { it !in candidateStateSet }
                .let {
                    candidateStates.addAll(it)
                    candidateStateSet.addAll(it)
                }
        }

        throw IllegalStateException("Unable to compute solution, explored ${exploredStates.size} states")
    }

    private fun computeChildStates(): List<Burrow> = prawns
        .flatMap { prawn ->
            prawn.generatePossiblePaths(this).map { path ->
                Burrow(
                    this,
                    prawns.filter { it != prawn } + prawn.moveAlong(path),
                    navGrid,
                    adjacentPositionMap
                )
            }
        }
        .groupBy { it }
        .mapValues { (_, v) -> v.minByOrNull { burrow -> burrow.spentEnergy }!! }
        .values
        .toList()

    private fun isOrganised(): Boolean = prawns.all(Prawn::isOrganised)

    fun display(): String {
        return IntRange(navGrid.minOf(Vec2i::y) - 1, navGrid.maxOf(Vec2i::y) + 1)
            .joinToString("\n") { y ->
                IntRange(navGrid.minOf(Vec2i::x) - 1, navGrid.maxOf(Vec2i::x) + 1)
                    .joinToString("") { x ->
                        val position = Vec2i(x, y)
                        if (position in prawnPositions) {
                            when (val prawn = prawns.find { it.position == position }!!) {
                                is AmberPrawn -> "A"
                                is BronzePrawn -> "B"
                                is CopperPrawn -> "C"
                                is DesertPrawn -> "D"
                                else -> throw IllegalStateException("Unhandled prawn: $prawn")
                            }
                        } else if (position in navGrid) {
                            "."
                        } else if (y <= 1 || x in 1..9) {
                            "#"
                        } else {
                            " "
                        }
                    }
            }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Burrow) return false

        if (prawns != other.prawns) return false

        return true
    }

    override fun hashCode(): Int {
        return prawns.hashCode()
    }

    override fun toString(): String {
        return "Burrow(prawns=$prawns, spentEnergy=$spentEnergy)"
    }

}
