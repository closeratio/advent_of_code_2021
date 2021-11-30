package com.closeratio.aoc2021.common.math

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
    operator fun times(other: Vec2i): Vec2i = Vec2i(x * other.x, y * other.y)

}
