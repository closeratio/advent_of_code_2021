package com.closeratio.aoc2021.common.math

import kotlin.math.absoluteValue
import kotlin.math.pow

data class Vec2i(
    val x: Int,
    val y: Int
) {

    companion object {
        val ZERO = Vec2i(0, 0)
    }

    fun length(): Double = (x.toDouble().pow(2) + y.toDouble().pow(2)).pow(0.5)

    operator fun plus(other: Vec2i): Vec2i = Vec2i(x + other.x, y + other.y)
    operator fun minus(other: Vec2i): Vec2i = Vec2i(x - other.x, y - other.y)
    operator fun times(other: Vec2i): Vec2i = Vec2i(x * other.x, y * other.y)

    fun isDiagonal(): Boolean = x != 0 && y != 0

    fun up(): Vec2i = Vec2i(x, y - 1)
    fun down(): Vec2i = Vec2i(x, y + 1)
    fun left(): Vec2i = Vec2i(x - 1, y)
    fun right(): Vec2i = Vec2i(x + 1, y)

    fun adjacent(): Set<Vec2i> = setOf(
        up(),
        down(),
        left(),
        right()
    )

    fun diagonallyAdjacent(): Set<Vec2i> = setOf(
        up().left(),
        up().right(),
        down().left(),
        down().right()
    )

    fun manhattanDistance(): Int = x.absoluteValue + y.absoluteValue

}
