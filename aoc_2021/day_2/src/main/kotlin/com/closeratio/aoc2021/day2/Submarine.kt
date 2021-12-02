package com.closeratio.aoc2021.day2

import com.closeratio.aoc2021.common.math.Vec2i

abstract class Submarine(
    protected val position: Vec2i
) {

    abstract fun forward(amount: Int): Submarine
    abstract fun down(amount: Int): Submarine
    abstract fun up(amount: Int): Submarine

    fun calculatePositionDigest(): Int = position.x * position.y

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Submarine

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(position=$position)"
    }

}
