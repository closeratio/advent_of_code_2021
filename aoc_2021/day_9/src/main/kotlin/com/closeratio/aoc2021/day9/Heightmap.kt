package com.closeratio.aoc2021.day9

import com.closeratio.aoc2021.common.math.Vec2i

data class Heightmap(
    val map: Map<Vec2i, Long>
) {

    companion object {
        fun parse(input: String): Heightmap = input
            .trim()
            .split("\n")
            .map(String::trim)
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char -> Vec2i(x, y) to char.toString().toLong() }
            }
            .toMap()
            .let(::Heightmap)
    }

    fun riskLevelSum(): Long = map
        .filter { (vec, height) ->
            vec.adjacent()
                .mapNotNull { map[it] }
                .all { it > height }
        }
        .values
        .sumOf { it + 1 }

    fun findBasins(): List<Basin> {
        val basins = arrayListOf<Basin>()

        val unexploredPositions = map
            .filter { (_, v) -> v < 9 }
            .map { (k, _) -> k }
            .toMutableSet()

        while (unexploredPositions.isNotEmpty()) {
            val position = unexploredPositions.first()
            val currentBasinPositions = hashSetOf<Vec2i>()
            explorePosition(position, currentBasinPositions, unexploredPositions)
            basins.add(Basin(currentBasinPositions))
        }

        return basins
    }

    private fun explorePosition(
        position: Vec2i,
        currentBasinPositions: MutableSet<Vec2i>,
        unexploredPositions: MutableSet<Vec2i>
    ) {
        currentBasinPositions.add(position)
        unexploredPositions.remove(position)

        position.adjacent()
            .filter { it in unexploredPositions }
            .forEach { explorePosition(it, currentBasinPositions, unexploredPositions) }
    }

    fun basinFactor(): Long = findBasins()
        .sortedByDescending { it.positions.size }
        .take(3)
        .map { it.positions.size.toLong() }
        .let { (a, b, c) -> a * b * c }

}
