package com.closeratio.aoc2021.day15

import com.closeratio.aoc2021.common.math.Vec2i
import java.util.*

data class RiskMap(
    val template: Map<Vec2i, Long>
) {

    private val templateWidth = template.keys.maxOf(Vec2i::x) + 1
    private val templateHeight = template.keys.maxOf(Vec2i::y) + 1
    private val width = templateWidth * 5
    private val height = templateHeight * 5

    companion object {
        fun parse(input: String): RiskMap = input
            .trim()
            .split("\n")
            .map(String::trim)
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char ->
                    Vec2i(x, y) to char.toString().toLong()
                }
            }
            .toMap()
            .let(::RiskMap)
    }

    fun calculatePathRisk(): Long = calculatePathRisk(
        Vec2i.ZERO,
        Vec2i(
            template.keys.maxOf(Vec2i::x),
            template.keys.maxOf(Vec2i::y)
        )
    )

    fun calculatePathRisk(
        start: Vec2i,
        finish: Vec2i
    ): Long {
        val explored = linkedSetOf<Vec2i>()
        val candidates = PriorityQueue(Comparator.comparingLong(AccumulatedPositionRisk::risk))
            .apply {
                add(AccumulatedPositionRisk(start, 0))
            }

        while (candidates.isNotEmpty()) {
            val current = candidates.poll()

            if (current.position == finish) {
                return current.risk
            }

            explored.add(current.position)
            current
                .position
                .adjacent()
                .asSequence()
                .filter { it.x >= 0 && it.y >= 0 && it.x < width && it.y < height }
                .filter { it !in explored }
                .map { AccumulatedPositionRisk(it, current.risk + getRiskLevel(it)) }
                .filter { it !in candidates }
                .forEach(candidates::add)
        }

        throw IllegalStateException("Unable to find path from $start to $finish")
    }

    fun getRiskLevel(position: Vec2i): Long {
        val xMultiple = position.x / templateWidth
        val xOffset = position.x % templateWidth

        val yMultiple = position.y / templateHeight
        val yOffset = position.y % templateHeight

        val risk = template.getValue(Vec2i(xOffset, yOffset)) + xMultiple + yMultiple
        return if (risk < 10) {
            risk
        } else {
            (risk % 10) + 1
        }
    }

}