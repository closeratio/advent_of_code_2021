package com.closeratio.aoc2021.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class InstructionsTest {

    private val instructions = Instructions.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(instructions.template, `is`(PolymerTemplate("NNCB")))
        assertThat(instructions.insertionRules, hasSize(16))
    }

    @Test
    fun quantityDifference() {
        val result = instructions.quantityDifference(10)
        assertThat(result, `is`(1588))
    }

}
