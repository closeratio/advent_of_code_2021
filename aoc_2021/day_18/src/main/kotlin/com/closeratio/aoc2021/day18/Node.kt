package com.closeratio.aoc2021.day18

abstract class Node(
    var nestLevel: Int
) {
    internal var parent: Node? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false

        if (nestLevel != other.nestLevel) return false

        return true
    }

    override fun hashCode(): Int {
        return nestLevel
    }

}

