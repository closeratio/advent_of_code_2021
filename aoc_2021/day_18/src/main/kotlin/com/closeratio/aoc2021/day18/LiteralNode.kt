package com.closeratio.aoc2021.day18

class LiteralNode(
    nestLevel: Int,
    val value: Long
): Node(nestLevel) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LiteralNode) return false
        if (!super.equals(other)) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    override fun toString(): String {
        return "$value"
    }
}