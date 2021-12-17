package com.closeratio.aoc2021.day15

import com.closeratio.aoc2021.common.math.Vec2i
import java.util.*

data class RiskMap(
    val map: Map<Vec2i, Long>
) {

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
            map.keys.maxOf(Vec2i::x),
            map.keys.maxOf(Vec2i::y)
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
        val prevMap = HashMap<Vec2i, Vec2i>()

        while (candidates.isNotEmpty() && finish !in prevMap) {
            val current = candidates.poll()
            explored.add(current.position)
            current
                .position
                .adjacent()
                .asSequence()
                .filter { it in map }
                .filter { it !in explored }
                .map { AccumulatedPositionRisk(it, current.risk + map.getValue(it)) }
                .filter { it !in candidates }
                .onEach {
                    if (it.position !in prevMap) {
                        prevMap[it.position] = current.position
                    }
                }
                .forEach(candidates::add)
        }

        if (finish !in prevMap) {
            throw IllegalStateException("Unable to find path from $start to $finish")
        }

        val path = linkedSetOf(finish)
        while (start !in path) {
            path.add(prevMap.getValue(path.last()))
        }

        return path.map(map::getValue).sum() - map.getValue(start)
    }

}