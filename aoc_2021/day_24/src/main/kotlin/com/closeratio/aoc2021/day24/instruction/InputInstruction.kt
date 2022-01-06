package com.closeratio.aoc2021.day24.instruction

import com.closeratio.aoc2021.common.text.CharBuffer
import com.closeratio.aoc2021.day24.VariableState
import com.closeratio.aoc2021.day24.VariableValue

class InputInstruction(
    private val targetVariable: VariableValue
) : Instruction() {

    override fun apply(variableState: VariableState, inputBuffer: CharBuffer): VariableState = inputBuffer
        .remove()
        .toString()
        .toLong()
        .let {
            variableState.setVariable(targetVariable.variableName, it)
        }

}