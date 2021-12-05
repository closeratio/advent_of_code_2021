package com.closeratio.aoc2021.day5

import com.closeratio.aoc2021.common.math.Line
import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class LineParserTest {

    @Test
    fun parseLines() {
        val lines = LineParser().parseLines(javaClass.getResource("/test_input.txt")!!.readText())

        assertThat(lines, hasSize(10))
        assertThat(
            lines, contains(
                Line(Vec2i(0, 9), Vec2i(5, 9)),
                Line(Vec2i(8, 0), Vec2i(0, 8)),
                Line(Vec2i(9, 4), Vec2i(3, 4)),
                Line(Vec2i(2, 2), Vec2i(2, 1)),
                Line(Vec2i(7, 0), Vec2i(7, 4)),
                Line(Vec2i(6, 4), Vec2i(2, 0)),
                Line(Vec2i(0, 9), Vec2i(2, 9)),
                Line(Vec2i(3, 4), Vec2i(1, 4)),
                Line(Vec2i(0, 0), Vec2i(8, 8)),
                Line(Vec2i(5, 5), Vec2i(8, 2))
            )
        )
    }

}