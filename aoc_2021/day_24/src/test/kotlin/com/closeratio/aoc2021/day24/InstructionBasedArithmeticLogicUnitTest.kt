package com.closeratio.aoc2021.day24

import com.closeratio.aoc2021.day24.alu.InstructionBasedArithmeticLogicUnit
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class InstructionBasedArithmeticLogicUnitTest {

    private val alu = InstructionBasedArithmeticLogicUnit.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(alu.program, hasSize(11))
    }

    @Test
    fun isModelNumberValid() {
        assertThat(alu.isModelNumberValid(0), `is`(true to 0L))
        assertThat(alu.isModelNumberValid(2), `is`(true to 0L))
        assertThat(alu.isModelNumberValid(4), `is`(true to 0L))
        assertThat(alu.isModelNumberValid(6), `is`(true to 0L))
        assertThat(alu.isModelNumberValid(8), `is`(true to 0L))

        assertThat(alu.isModelNumberValid(1), `is`(false to 1L))
        assertThat(alu.isModelNumberValid(3), `is`(false to 1L))
        assertThat(alu.isModelNumberValid(5), `is`(false to 1L))
        assertThat(alu.isModelNumberValid(7), `is`(false to 1L))
        assertThat(alu.isModelNumberValid(9), `is`(false to 1L))
    }

}