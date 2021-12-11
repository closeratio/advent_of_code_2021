package com.closeratio.aoc2021.day10

data class Chunk(
    val openingCharacter: Char,
    val innerChunks: List<Chunk>,
    val completionChar: Char?
) {

    companion object {

        private fun getClosingChar(openingChar: Char): Char = when (openingChar) {
            '(' -> ')'
            '[' -> ']'
            '{' -> '}'
            '<' -> '>'
            else -> throw IllegalArgumentException("Unknown opening char: $openingChar")
        }

        fun parseLine(input: String): List<Chunk> {
            val chunks = mutableListOf<Chunk>()
            val chars = input.toMutableList()
            while (chars.isNotEmpty()) {
                chunks.add(parseChunk(chars))
            }

            return chunks
        }

        private fun parseChunk(input: MutableList<Char>): Chunk {
            val openingChar = input.removeAt(0)
            val expectedClosingChar: Char = getClosingChar(openingChar)

            val chunks = mutableListOf<Chunk>()

            while (input.isNotEmpty() && !input.first().isClosingChar()) {
                chunks.add(parseChunk(input))
            }

            val incompleteChar = if (input.isNotEmpty()) {
                val closingChar = input.removeAt(0)

                if (closingChar != expectedClosingChar) {
                    throw SyntaxException(expectedClosingChar, closingChar)
                }

                null
            } else {
                expectedClosingChar
            }

            return Chunk(
                openingChar,
                chunks,
                incompleteChar
            )
        }
    }

    fun getCompletionChars(): List<Char> {
        val innerCompletionChars = innerChunks.lastOrNull()?.getCompletionChars() ?: listOf()
        return if (completionChar != null) {
            innerCompletionChars + completionChar
        } else {
            innerCompletionChars
        }
    }

}
