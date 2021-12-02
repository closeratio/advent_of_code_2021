package com.closeratio.aoc2021.day2

import com.closeratio.aoc2021.common.math.Vec2i

class ComplexSubmarine(
    position: Vec2i,
    val aim: Int
) : Submarine(position) {

    override fun forward(amount: Int): ComplexSubmarine = ComplexSubmarine(
        position + Vec2i(amount, aim * amount), aim
    )

    override fun down(amount: Int): ComplexSubmarine = ComplexSubmarine(
        position, aim + amount
    )

    override fun up(amount: Int): ComplexSubmarine = ComplexSubmarine(
        position, aim - amount
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ComplexSubmarine

        if (aim != other.aim) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + aim
        return result
    }


}
