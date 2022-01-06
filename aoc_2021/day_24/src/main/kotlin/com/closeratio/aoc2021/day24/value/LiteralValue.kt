package com.closeratio.aoc2021.day24.value

import com.closeratio.aoc2021.day24.VariableState

data class LiteralValue(
    val value: Long
): Value() {

    override fun value(variableState: VariableState): Long = value
}