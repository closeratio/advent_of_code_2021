package com.closeratio.aoc2021.common.math

import com.closeratio.aoc2021.common.math.Line.Direction.*
import kotlin.math.absoluteValue

data class Line(
    val start: Vec2i,
    val end: Vec2i
) {

    private enum class Direction {
        ASCENDING,
        DESCENDING,
        NONE
    }

    fun isDiagonal(): Boolean = (end - start).isDiagonal()

    fun points(): Set<Vec2i> {

        val delta: Vec2i = end - start
        val deltaValue = (if (delta.x != 0) delta.x else delta.y).absoluteValue

        val xDirection: Direction = when {
            delta.x > 0 -> ASCENDING
            delta.x < 0 -> DESCENDING
            else -> NONE
        }

        val yDirection: Direction = when {
            delta.y > 0 -> ASCENDING
            delta.y < 0 -> DESCENDING
            else -> NONE
        }

        return IntRange(0, deltaValue)
            .map { increment ->
                Vec2i(
                    when (xDirection) {
                        ASCENDING -> increment
                        DESCENDING -> -increment
                        NONE -> 0
                    },
                    when (yDirection) {
                        ASCENDING -> increment
                        DESCENDING -> -increment
                        NONE -> 0
                    }
                )
            }
            .map { offset ->
                start + offset
            }
            .toSet()
    }

}
