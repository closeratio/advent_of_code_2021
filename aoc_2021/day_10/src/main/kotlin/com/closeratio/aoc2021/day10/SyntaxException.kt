package com.closeratio.aoc2021.day10

data class SyntaxException(
    val expected: Char,
    val found: Char
): RuntimeException("Expected $expected but found $found instead")