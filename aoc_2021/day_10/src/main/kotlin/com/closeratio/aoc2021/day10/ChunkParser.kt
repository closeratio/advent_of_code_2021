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
                Chunk.parseLine(it)
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

    fun middleCompletionScore(): Long {
        val scores = lines
            .mapNotNull {
                try {
                    Chunk.parseLine(it).last()
                } catch (e: SyntaxException) {
                    null
                }
            }
            .map(Chunk::getCompletionChars)
            .map { chars ->
                chars.fold(0L) { acc, curr ->
                    (acc * 5) + when (curr) {
                        ')' -> 1
                        ']' -> 2
                        '}' -> 3
                        '>' -> 4
                        else -> throw IllegalArgumentException("Unhandled completion char: $curr")
                    }
                }
            }
            .sorted()

        return scores[scores.size / 2]
    }


}