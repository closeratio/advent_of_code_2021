package com.closeratio.aoc2021.day24.value

import com.closeratio.aoc2021.day24.VariableState

data class VariableValue(
    val variableName: Char
): Value() {

    override fun value(variableState: VariableState): Long = variableState.getVariable(variableName)

}