package com.closeratio.aoc2021.day10

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ChunkParserTest {

    private val parser = ChunkParser(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun syntaxErrorScore() {
        val result = parser.syntaxErrorScore()
        assertThat(result, `is`(26397))
    }

    @Test
    fun middleCompletionScore() {
        val result = parser.middleCompletionScore()
        assertThat(result, `is`(288957))
    }

}