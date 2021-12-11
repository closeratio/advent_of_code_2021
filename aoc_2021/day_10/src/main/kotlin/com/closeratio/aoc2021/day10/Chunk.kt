package com.closeratio.aoc2021.day10

data class Chunk(
    val openingCharacter: Char,
    val innerChunks: List<Chunk>
) {

    companion object {
        fun parse(input: MutableList<Char>): Chunk {
            val openingChar = input.removeAt(0)
            val expectedClosingChar: Char = when (openingChar) {
                '(' -> ')'
                '[' -> ']'
                '{' -> '}'
                '<' -> '>'
                else -> throw IllegalArgumentException("Unknown opening char: $openingChar")
            }

            val chunks = mutableListOf<Chunk>()

            while (input.isNotEmpty() && !input.first().isClosingChar()) {
                chunks.add(parse(input))
            }

            if (input.isNotEmpty()) {
                val closingChar = input.removeAt(0)

                if (closingChar != expectedClosingChar) {
                    throw SyntaxException(expectedClosingChar, closingChar)
                }
            }

            return Chunk(
                openingChar,
                chunks
            )
        }
    }

}
