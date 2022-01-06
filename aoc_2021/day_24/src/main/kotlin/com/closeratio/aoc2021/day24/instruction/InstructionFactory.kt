package com.closeratio.aoc2021.day24.instruction

import com.closeratio.aoc2021.day24.LiteralValue
import com.closeratio.aoc2021.day24.VariableValue

object InstructionFactory {

    fun parse(line: String): Instruction = line
        .split(" ")
        .let { elements ->
            val firstElement = VariableValue(elements[1].single())
            val secondElement = elements
                .getOrNull(2)
                ?.let {
                    val value = it.toLongOrNull()
                    if (value != null) {
                        LiteralValue(value)
                    } else {
                        VariableValue(it.single())
                    }
                }

            when (elements.first()) {
                "inp" -> InputInstruction(firstElement)
                "add" -> AddInstruction(firstElement, secondElement!!)
                "mul" -> MultiplyInstruction(firstElement, secondElement!!)
                "div" -> DivideInstruction(firstElement, secondElement!!)
                "mod" -> ModuloInstruction(firstElement, secondElement!!)
                "eql" -> EqualsInstruction(firstElement, secondElement!!)
                else -> throw IllegalArgumentException("Unrecognised instruction: $line")
            }
        }

}