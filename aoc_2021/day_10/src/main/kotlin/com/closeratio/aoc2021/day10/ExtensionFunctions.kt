package com.closeratio.aoc2021.day10

fun Char.isClosingChar(): Boolean = when (this) {
    ')', ']', '}', '>' -> true
    else -> false
}