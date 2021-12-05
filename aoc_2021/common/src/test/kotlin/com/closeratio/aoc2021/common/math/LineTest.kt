package com.closeratio.aoc2021.common.math

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LineTest {

    companion object {
        @JvmStatic
        fun isDiagonalArguments(): List<Arguments> = listOf(
            Arguments.of(Line(Vec2i.ZERO, Vec2i.ZERO), false),
            Arguments.of(Line(Vec2i.ZERO, Vec2i(3, 0)), false),
            Arguments.of(Line(Vec2i.ZERO, Vec2i(-2, 0)), false),
            Arguments.of(Line(Vec2i.ZERO, Vec2i(0, 4)), false),
            Arguments.of(Line(Vec2i.ZERO, Vec2i(0, -5)), false),

            Arguments.of(Line(Vec2i(0, 10), Vec2i(0, -5)), false),
            Arguments.of(Line(Vec2i(0, 12), Vec2i(0, -5)), false),
            Arguments.of(Line(Vec2i(0, -10), Vec2i(0, -5)), false),

            Arguments.of(Line(Vec2i(10, 2), Vec2i(-5, 2)), false),
            Arguments.of(Line(Vec2i(12, 3), Vec2i(-5, 3)), false),
            Arguments.of(Line(Vec2i(-10, 4), Vec2i(-5, 4)), false),

            Arguments.of(Line(Vec2i(0, 4), Vec2i(2, 5)), true),
            Arguments.of(Line(Vec2i(4, -8), Vec2i(-5, 12)), true),
            Arguments.of(Line(Vec2i(-12, 2), Vec2i(50, -12)), true)
        )

        @JvmStatic
        fun pointsArguments(): List<Arguments> = listOf(
            Arguments.of(Line(Vec2i.ZERO, Vec2i.ZERO), setOf(Vec2i.ZERO)),

            Arguments.of(Line(Vec2i.ZERO, Vec2i(3, 0)), setOf(Vec2i.ZERO, Vec2i(1, 0), Vec2i(2, 0), Vec2i(3, 0))),
            Arguments.of(Line(Vec2i(1, 1), Vec2i(1, 3)), setOf(Vec2i(1, 1), Vec2i(1, 2), Vec2i(1, 3))),
            Arguments.of(Line(Vec2i(2, 3), Vec2i(-1, 3)), setOf(Vec2i(2, 3), Vec2i(1, 3), Vec2i(0, 3), Vec2i(-1, 3))),

            Arguments.of(Line(Vec2i.ZERO, Vec2i(2, 2)), setOf(Vec2i.ZERO, Vec2i(1, 1), Vec2i(2, 2))),
            Arguments.of(Line(Vec2i.ZERO, Vec2i(-3, -3)), setOf(Vec2i.ZERO, Vec2i(-1, -1), Vec2i(-2, -2), Vec2i(-3, -3))),
            Arguments.of(Line(Vec2i.ZERO, Vec2i(-4, 4)), setOf(Vec2i.ZERO, Vec2i(-1, 1), Vec2i(-2, 2), Vec2i(-3, 3), Vec2i(-4, 4))),
            Arguments.of(Line(Vec2i.ZERO, Vec2i(5, -5)), setOf(Vec2i.ZERO, Vec2i(1, -1), Vec2i(2, -2), Vec2i(3, -3), Vec2i(4, -4), Vec2i(5, -5)))
        )

    }

    @ParameterizedTest
    @MethodSource("isDiagonalArguments")
    fun isDiagonal(
        line: Line,
        expectedValue: Boolean
    ) {
        assertThat(line.isDiagonal(), `is`(expectedValue))
    }

    @ParameterizedTest
    @MethodSource("pointsArguments")
    fun points(
        line: Line,
        expected: Set<Vec2i>
    ) {
        val result: Set<Vec2i> = line.points()

        assertThat(result, hasSize(expected.size))
        assertThat(result, `is`(expected))
    }

}