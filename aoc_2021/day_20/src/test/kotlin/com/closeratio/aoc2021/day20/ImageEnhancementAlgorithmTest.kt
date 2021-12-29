package com.closeratio.aoc2021.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ImageEnhancementAlgorithmTest {

    private val imageEnhancementAlgorithm = javaClass
        .getResource("/test_input.txt")!!
        .readText()
        .trim()
        .split("\n\n")
        .first()
        .let(::ImageEnhancementAlgorithm)

    companion object {
        @JvmStatic
        fun runAlgorithmArguments(): List<Arguments> = listOf(
            // 33
            Arguments.of(listOf(false, false, false, true, false, false, false, false, true), false),
            // 34
            Arguments.of(listOf(false, false, false, true, false, false, false, true, false), true),
            // 35
            Arguments.of(listOf(false, false, false, true, false, false, false, true, true), true),
            // 36
            Arguments.of(listOf(false, false, false, true, false, false, true, false, false), false)
        )
    }

    @ParameterizedTest
    @MethodSource("runAlgorithmArguments")
    fun runAlgorithm(
        input: List<Boolean>,
        expected: Boolean
    ) {
        val result = imageEnhancementAlgorithm.runAlgorithm(input)
        assertThat(result, `is`(expected))
    }

}