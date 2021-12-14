package com.closeratio.aoc2021.day13

import com.closeratio.aoc2021.common.math.Vec2i
import com.closeratio.aoc2021.day13.FoldInstruction.Direction.HORIZONTAL
import com.closeratio.aoc2021.day13.FoldInstruction.Direction.VERTICAL
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.IsIterableContaining
import org.junit.jupiter.api.Test

class ManualTest {

    private val manual = Manual.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(manual.dots, hasSize(18))
        assertThat(
            manual.dots, IsIterableContaining.hasItems(
                Vec2i(4, 11),
                Vec2i(1, 10)
            )
        )

        assertThat(manual.foldInstructions, hasSize(2))
        assertThat(
            manual.foldInstructions, contains(
                FoldInstruction(HORIZONTAL, 7),
                FoldInstruction(VERTICAL, 5)
            )
        )
    }

    @Test
    fun fold() {
        val result = manual.fold(manual.foldInstructions.first(), manual.dots)

        assertThat(result.visibleDotCount, `is`(17))
        assertThat(result.printed, `is`("""
            #.##..#..#.
            #...#......
            ......#...#
            #...#......
            .#.#..#.###
        """.trimIndent()))
    }

    @Test
    fun executeInstructions() {
        val result = manual.executeInstructions()

        assertThat(result.visibleDotCount, `is`(16))
        assertThat(result.printed, `is`("""
            #####
            #...#
            #...#
            #...#
            #####
        """.trimIndent()))
    }

}