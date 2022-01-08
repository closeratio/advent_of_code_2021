package com.closeratio.aoc2021.day24

import com.closeratio.aoc2021.common.text.CharBuffer
import com.closeratio.aoc2021.day24.instruction.Instruction
import com.closeratio.aoc2021.day24.instruction.InstructionFactory

class ArithmeticLogicUnit(
    val program: List<Instruction>
) {

    companion object {
        fun parse(input: String): ArithmeticLogicUnit = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map(InstructionFactory::parse)
            .let(::ArithmeticLogicUnit)
    }

    fun isModelNumberValid(modelNumber: Long): Boolean {
        val inputBuffer = CharBuffer(modelNumber.toString())

        val finalState = program.fold(VariableState(emptyMap())) { acc, curr -> curr.apply(acc, inputBuffer) }

        val z = finalState.getVariable('z')
        return z == 0L
    }

}