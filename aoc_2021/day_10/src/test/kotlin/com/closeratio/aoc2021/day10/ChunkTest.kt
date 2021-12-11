package com.closeratio.aoc2021.day10

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

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
                    Chunk.parse(it.toMutableList())
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

}