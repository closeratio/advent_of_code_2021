package com.closeratio.aoc2021.day24.instruction

import com.closeratio.aoc2021.common.text.CharBuffer
import com.closeratio.aoc2021.day24.VariableState
import com.closeratio.aoc2021.day24.value.Value
import com.closeratio.aoc2021.day24.value.VariableValue

class EqualsInstruction(
    private val firstValue: VariableValue,
    private val secondValue: Value
) : Instruction() {

    override fun apply(variableState: VariableState, inputBuffer: CharBuffer): VariableState =
        (firstValue.value(variableState) == secondValue.value(variableState))
            .let {
                variableState.setVariable(
                    firstValue.variableName, when (it) {
                        true -> 1
                        false -> 0
                    }
                )
            }

}