package com.closeratio.aoc2021.day24.alu

import com.closeratio.aoc2021.common.text.CharBuffer
import com.closeratio.aoc2021.day24.VariableState
import com.closeratio.aoc2021.day24.instruction.Instruction
import com.closeratio.aoc2021.day24.instruction.InstructionFactory

class InstructionBasedArithmeticLogicUnit(
    val program: List<Instruction>
): ArithmeticLogicUnit() {

    companion object {
        fun parse(input: String): InstructionBasedArithmeticLogicUnit = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map(InstructionFactory::parse)
            .let(::InstructionBasedArithmeticLogicUnit)
    }

    override fun isModelNumberValid(modelNumber: Long): Pair<Boolean, Long> {
        val inputBuffer = CharBuffer(modelNumber.toString())

        val finalState = program.fold(VariableState(emptyMap())) { acc, curr -> curr.apply(acc, inputBuffer) }

        val z = finalState.getVariable('z')
        return (z == 0L) to z
    }

}