package com.closeratio.aoc2021.day24.instruction

import com.closeratio.aoc2021.common.text.CharBuffer
import com.closeratio.aoc2021.day24.Value
import com.closeratio.aoc2021.day24.VariableState
import com.closeratio.aoc2021.day24.VariableValue

class MultiplyInstruction(
    private val firstValue: VariableValue,
    private val secondValue: Value
) : Instruction() {

    override fun apply(variableState: VariableState, inputBuffer: CharBuffer): VariableState =
        (firstValue.value(variableState) * secondValue.value(variableState))
            .let { variableState.setVariable(firstValue.variableName, it) }

}