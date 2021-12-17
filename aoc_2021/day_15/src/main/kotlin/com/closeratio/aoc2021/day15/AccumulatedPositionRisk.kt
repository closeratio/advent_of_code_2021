package com.closeratio.aoc2021.day15

import com.closeratio.aoc2021.common.math.Vec2i

class AccumulatedPositionRisk(
    val position: Vec2i,
    val risk: Long
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccumulatedPositionRisk) return false

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "AccumulatedPositionRisk(position=$position, risk=$risk)"
    }

}
