package com.closeratio.aoc2021.day22

import com.closeratio.aoc2021.common.math.Vec3i
import com.closeratio.aoc2021.day22.Instruction.Type.TURN_OFF
import com.closeratio.aoc2021.day22.Instruction.Type.TURN_ON
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder
import org.junit.jupiter.api.Test

class InstructionProcessorTest {

    @Test
    fun parse() {
        val processor = InstructionProcessor.parse(javaClass.getResource("/test_input_1.txt")!!.readText())

        assertThat(processor.instructions, hasSize(4))
        assertThat(processor.instructions, IsIterableContainingInOrder.contains(
            Instruction(Cuboid(Vec3i(10, 10, 10), Vec3i(12, 12, 12)), TURN_ON),
            Instruction(Cuboid(Vec3i(11, 11, 11), Vec3i(13, 13, 13)), TURN_ON),
            Instruction(Cuboid(Vec3i(9, 9, 9), Vec3i(11, 11, 11)), TURN_OFF),
            Instruction(Cuboid(Vec3i(10, 10, 10), Vec3i(10, 10, 10)), TURN_ON)
        ))
    }

    @Test
    fun processInstructionsSimple() {
        val result = InstructionProcessor.parse(javaClass.getResource("/test_input_1.txt")!!.readText()).processInstructions()

        assertThat(result, `is`(39))
    }

    @Test
    fun processInstructionsComplex() {
        val result = InstructionProcessor.parse(javaClass.getResource("/test_input_2.txt")!!.readText()).processInstructions()

        assertThat(result, `is`(590784))
    }

}