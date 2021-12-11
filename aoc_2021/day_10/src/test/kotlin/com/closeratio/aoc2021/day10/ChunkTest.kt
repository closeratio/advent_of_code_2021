package com.closeratio.aoc2021.day10

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ChunkTest {

    @Test
    fun parse() {
        val result = javaClass.getResource("/test_input.txt")!!
            .readText()
            .trim()
            .split("\n")
            .map(String::trim)
            .mapNotNull {
                try {
                    Chunk.parseLine(it)
                    null
                } catch (e: SyntaxException) {
                    e
                }
            }

        assertThat(result, hasSize(5))
        assertThat(
            result, contains(
                SyntaxException(']', '}'),
                SyntaxException(']', ')'),
                SyntaxException(')', ']'),
                SyntaxException('>', ')'),
                SyntaxException(']', '>')
            )
        )
    }

    companion object {
        @JvmStatic
        fun getCompletionCharsArguments(): List<Arguments> = listOf(
            Arguments.of("[({(<(())[]>[[{[]{<()<>>", "}}]])})]"),
            Arguments.of("[(()[<>])]({[<{<<[]>>(", ")}>]})"),
            Arguments.of("(((({<>}<{<{<>}{[]{[]{}", "}}>}>))))"),
            Arguments.of("{<[[]]>}<{[{[{[]{()[[[]", "]]}}]}]}>"),
            Arguments.of("<{([{{}}[<[[[<>{}]]]>[]]", "])}>")
        )
    }

    @ParameterizedTest
    @MethodSource("getCompletionCharsArguments")
    fun getCompletionChars(
        input: String,
        expected: String
    ) {
        val chunks = Chunk.parseLine(input)
        val completionChars = chunks
            .last()
            .getCompletionChars()
            .joinToString("")
        assertThat(completionChars, `is`(expected))
    }

}