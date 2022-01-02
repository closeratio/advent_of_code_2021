package com.closeratio.aoc2021.day22

import kotlin.math.max
import kotlin.math.min

fun IntRange.intersection(other: IntRange): IntRange {
    return max(first, other.first)..min(last, other.last)
}