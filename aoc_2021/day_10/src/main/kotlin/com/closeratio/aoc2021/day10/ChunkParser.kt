package com.closeratio.aoc2021.day10

data class ChunkParser(
    val input: String
) {

    val lines = input
        .trim()
        .split("\n")
        .map(String::trim)

    fun syntaxErrorScore(): Long = lines
        .mapNotNull {
            try {
                Chunk.parse(it.toMutableList())
                null
            } catch (e: SyntaxException) {
                e
            }
        }
        .map(SyntaxException::found)
        .sumOf {
            when (it) {
                ')' -> 3L
                ']' -> 57L
                '}' -> 1197L
                '>' -> 25137L
                else -> throw IllegalArgumentException("Unknown closing char: $it")
            }
        }


}