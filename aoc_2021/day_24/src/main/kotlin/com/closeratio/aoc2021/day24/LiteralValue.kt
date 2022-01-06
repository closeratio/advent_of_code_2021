package com.closeratio.aoc2021.day24

data class LiteralValue(
    val value: Long
): Value() {

    override fun value(variableState: VariableState): Long = value
}