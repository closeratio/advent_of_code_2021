package com.closeratio.aoc2021.day24

import com.closeratio.aoc2021.day24.alu.HardcodedArithmeticLogicUnit
import com.closeratio.aoc2021.day24.alu.InstructionBasedArithmeticLogicUnit
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class HardcodedArithmeticLogicUnitTest {

    private val referenceAlu =
        InstructionBasedArithmeticLogicUnit.parse(javaClass.getResource("/test_input.txt")!!.readText())
    private val alu = HardcodedArithmeticLogicUnit()

    @Test
    fun isModelNumberValid() {
        LongRange(11111111111100, 11111111111500)
            .filterNot { it.toString().contains("0") }
            .forEach {
                val expected = referenceAlu.isModelNumberValid(it)
                val result = alu.isModelNumberValid(it)

                assertThat("For input value $it, result should be $expected but is $result", result, `is`(expected))
            }
    }

}