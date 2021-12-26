package com.closeratio.aoc2021.day18

import java.util.*

abstract class Node {
    internal val searchId: UUID = UUID.randomUUID()

    abstract fun magnitude(): Long

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false
        if (other.javaClass != javaClass) return false

        if (searchId != other.searchId) return false

        return true
    }

    override fun hashCode(): Int {
        return searchId.hashCode()
    }


}

