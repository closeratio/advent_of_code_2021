package com.closeratio.aoc2021.day18

class LiteralNode(
    var value: Long
): Node() {

    override fun magnitude(): Long = value

    override fun toString(): String {
        return "$value"
    }
}