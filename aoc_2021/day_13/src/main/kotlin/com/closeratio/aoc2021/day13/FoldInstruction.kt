package com.closeratio.aoc2021.day13

data class FoldInstruction(
    val direction: Direction,
    val amount: Int
) {

    enum class Direction {
        HORIZONTAL,
        VERTICAL
    }

}
