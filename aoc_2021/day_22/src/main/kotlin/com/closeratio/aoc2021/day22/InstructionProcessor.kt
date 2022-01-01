package com.closeratio.aoc2021.day22

import com.closeratio.aoc2021.day22.Instruction.Type.TURN_ON

class InstructionProcessor(
    val instructions: List<Instruction>
) {

    companion object {
        fun parse(input: String): InstructionProcessor = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map(Instruction.Companion::parse)
            .let(::InstructionProcessor)
    }

    fun processInstructions(): Long {
        val instructionSetList: MutableList<MutableList<Instruction>> = arrayListOf()

        instructions.forEach { instruction ->
            instructionSetList.forEach { it.add(instruction) }

            if (instruction.type == TURN_ON) {
                instructionSetList.add(arrayListOf(instruction))
            }
        }

        return instructionSetList
            .map { list ->
                InstructionSet(
                    Region(
                        list.first().cuboid,
                        list.first().cuboid.pointCount
                    ),
                    list.drop(1)
                )
            }
            .sumOf { it.applyInstructions() }
    }

}