package com.closeratio.aoc2021.day24.instruction

import com.closeratio.aoc2021.common.text.CharBuffer
import com.closeratio.aoc2021.day24.VariableState

abstract class Instruction {

    abstract fun apply(
        variableState: VariableState,
        inputBuffer: CharBuffer
    ): VariableState

}

