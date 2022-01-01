package com.closeratio.aoc2021.day22

class InstructionSet(
    private val region: Region,
    private val instructions: List<Instruction>
) {

    fun applyInstructions(): Long {
        return instructions
            .fold(region to listOf<Instruction>()) { (region, previousInstructions), instruction ->
                generateNewRegion(region, instruction, previousInstructions) to previousInstructions + instruction
            }
            .first
            .onCubes
    }

    private fun generateNewRegion(
        currRegion: Region,
        instruction: Instruction,
        previousInstructions: List<Instruction>
    ): Region {
        // Figure out how much the current instruction affects the region (if at all)


        return currRegion
    }

}