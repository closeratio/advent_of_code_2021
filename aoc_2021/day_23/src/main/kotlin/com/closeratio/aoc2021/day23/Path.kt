package com.closeratio.aoc2021.day23

import com.closeratio.aoc2021.common.math.Vec2i

data class Path private constructor(
    val path: List<Vec2i>,
) {
    val start = path.first()
    val finish = path.last()

    companion object {
        fun from(path: List<Vec2i>): Path {
            // Check that the list contains adjacent positions
            path.zipWithNext().forEach { (first, second) ->
                if (first.minus(second).manhattanDistance() != 1) {
                    throw IllegalArgumentException("Bad path: $path")
                }
            }

            return Path(path)
        }
    }
}
