package com.closeratio.aoc2021.day17

import com.closeratio.aoc2021.common.math.Vec2i

data class TargetArea(
    val xRange: IntRange,
    val yRange: IntRange
) {

    fun contains(position: Vec2i): Boolean = position.x in xRange && position.y in yRange

}