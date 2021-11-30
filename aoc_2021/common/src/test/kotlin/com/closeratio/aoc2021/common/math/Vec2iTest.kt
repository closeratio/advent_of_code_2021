package com.closeratio.aoc2021.common.math

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class Vec2iTest {

    companion object {
        @JvmStatic
        fun powArguments(): List<Arguments> = listOf(
            Arguments.of(Vec2i.ZERO, 0),
            Arguments.of(Vec2i(3, 0), 3),
            Arguments.of(Vec2i(0, 2), 2),
            Arguments.of(Vec2i(-4, 0), 4),
            Arguments.of(Vec2i(0, -6), 6),
            Arguments.of(Vec2i(3, -4), 5)
        )

        @JvmStatic
        fun plusArguments(): List<Arguments> = listOf(
            Arguments.of(Vec2i.ZERO, Vec2i.ZERO, Vec2i.ZERO),
            Arguments.of(Vec2i(1, 0), Vec2i.ZERO, Vec2i(1, 0)),
            Arguments.of(Vec2i(0, -2), Vec2i.ZERO, Vec2i(0, -2)),
            Arguments.of(Vec2i(1, 0), Vec2i(0, 2), Vec2i(1, 2)),
            Arguments.of(Vec2i(-3, 0), Vec2i(9, 0), Vec2i(6, 0))
        )

        @JvmStatic
        fun timesArguments(): List<Arguments> = listOf(
            Arguments.of(Vec2i(2, 3), Vec2i.ZERO, Vec2i.ZERO),
            Arguments.of(Vec2i(3, 4), Vec2i(5, 6), Vec2i(15, 24)),
            Arguments.of(Vec2i(6, 7), Vec2i(-8, 9), Vec2i(-48, 63))
        )
    }

    @ParameterizedTest
    @MethodSource("powArguments")
    fun pow(
        vec: Vec2i,
        expectedLength: Double
    ) {
        assertThat(vec.length(), `is`(expectedLength))
    }

    @ParameterizedTest
    @MethodSource("plusArguments")
    fun plus(
        vec1: Vec2i,
        vec2: Vec2i,
        expected: Vec2i
    ) {
        val result = vec1 + vec2

        assertThat(result, `is`(expected))
    }

    @ParameterizedTest
    @MethodSource("timesArguments")
    fun times(
        vec1: Vec2i,
        vec2: Vec2i,
        expected: Vec2i
    ) {
        val result = vec1 * vec2

        assertThat(result, `is`(expected))
    }

}
