package com.closeratio.aoc2021.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HomeworkTest {

    companion object {
        @JvmStatic
        fun explodeArguments(): List<Arguments> = listOf(
            Arguments.of("[[[[[9,8],1],2],3],4]", "[[[[0,9],2],3],4]"),
            Arguments.of("[7,[6,[5,[4,[3,2]]]]]", "[7,[6,[5,[7,0]]]]"),
            Arguments.of("[[6,[5,[4,[3,2]]]],1]", "[[6,[5,[7,0]]],3]"),
            Arguments.of("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]", "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"),
            Arguments.of("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", "[[3,[2,[8,0]]],[9,[5,[7,0]]]]"),
        )

        @JvmStatic
        fun splitArguments(): List<Arguments> = listOf(
            Arguments.of(
                PairNode(LiteralNode(10), LiteralNode(1)),
                "[[5,5],1]"
            ),
            Arguments.of(
                PairNode(LiteralNode(11), LiteralNode(1)),
                "[[5,6],1]"
            ),
            Arguments.of(
                PairNode(LiteralNode(12), LiteralNode(1)),
                "[[6,6],1]"
            )
        )

        @JvmStatic
        fun sumMagnitudeArguments(): List<Arguments> = listOf(
            Arguments.of("[[1,2],[[3,4],5]]", 143),
            Arguments.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", 1384),
            Arguments.of("[[[[1,1],[2,2]],[3,3]],[4,4]]", 445),
            Arguments.of("[[[[3,0],[5,3]],[4,4]],[5,5]]", 791),
            Arguments.of("[[[[5,0],[7,4]],[5,5]],[6,6]]", 1137),
            Arguments.of("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", 3488)
        )

        @JvmStatic
        fun sumMagnitudeStringOnlyArguments(): List<Arguments> = listOf(
            Arguments.of(
                """
                    [1,1]
                    [2,2]
                    [3,3]
                    [4,4]
                """.trimIndent(),
                "[[[[1,1],[2,2]],[3,3]],[4,4]]"
            ),
            Arguments.of(
                """
                    [1,1]
                    [2,2]
                    [3,3]
                    [4,4]
                    [5,5]
                """.trimIndent(),
                "[[[[3,0],[5,3]],[4,4]],[5,5]]"
            ),
            Arguments.of(
                """
                    [1,1]
                    [2,2]
                    [3,3]
                    [4,4]
                    [5,5]
                    [6,6]
                """.trimIndent(),
                "[[[[5,0],[7,4]],[5,5]],[6,6]]"
            ),
        )
    }

    @ParameterizedTest
    @MethodSource("explodeArguments")
    fun explode(
        input: String,
        expected: String
    ) {
        val homework = Homework.parse(input)
        homework.explode(homework.lines.first())
        val result = homework.lines.first().toString()
        assertThat(result, `is`(expected))
    }

    @ParameterizedTest
    @MethodSource("splitArguments")
    fun split(
        input: PairNode,
        expected: String
    ) {
        // Bit of a code smell, having to create a homework instance unnecessarily. Suggests maybe that the logic should
        // be in a "service" class of some sort
        Homework.parse("[1,1]").split(input)
        val result = input.toString()
        assertThat(result, `is`(expected))
    }

    @Test
    fun reduce() {
        val homework = Homework.parse("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]")
        homework.reduce(homework.lines.first())
        val result = homework.lines.first()

        assertThat(result.toString(), `is`("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"))
    }

    @ParameterizedTest
    @MethodSource("sumMagnitudeArguments")
    fun sumMagnitude(
        input: String,
        expectedValue: Long
    ) {
        val homework = Homework.parse(input)
        val result = homework.sumMagnitude()

        assertThat(result.magnitude(), `is`(expectedValue))
    }

    @ParameterizedTest
    @MethodSource("sumMagnitudeStringOnlyArguments")
    fun sumMagnitudeStringOnly(
        input: String,
        expectedValue: String
    ) {
        val homework = Homework.parse(input)
        val result = homework.sumMagnitude()

        assertThat(result.toString(), `is`(expectedValue))
    }

    @Test
    fun sumMagnitudeComplex() {
        val homework = Homework.parse(javaClass.getResource("/test_input.txt")!!.readText())
        val result = homework.sumMagnitude()

        assertThat(result.toString(), `is`("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]"))
        assertThat(result.magnitude(), `is`(4140))
    }

    @Test
    fun reduceComplex() {
        val homework = Homework.parse("[[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]],[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]]")
        homework.reduce(homework.lines.single())

        assertThat(homework.lines.single().toString(), `is`("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"))
    }

    @Test
    fun sumMagnitude2Lines() {
        val homework = Homework.parse(
            """
                [[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
                [7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
            """.trimIndent()
        )
        val result = homework.sumMagnitude()

        assertThat(result.toString(), `is`("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"))
    }

}