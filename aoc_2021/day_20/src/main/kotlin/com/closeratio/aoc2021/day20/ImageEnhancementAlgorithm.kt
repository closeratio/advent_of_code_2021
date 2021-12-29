package com.closeratio.aoc2021.day20

class ImageEnhancementAlgorithm(
    private val algorithmData: String
) {

    private val litIndexes = algorithmData
        .mapIndexed { index, char -> index to char }
        .filter { (_, c) -> c == '#' }
        .map { (index, _) -> index }
        .toSet()

    fun firstIndexLit(): Boolean = 0 in litIndexes
    fun lastIndexLit(): Boolean = (algorithmData.length) in litIndexes

    fun runAlgorithm(
        input: List<Boolean>
    ): Boolean {
        assert(input.size == 9)
        val index = input
            .map { if (it) 1 else 0 }
            .joinToString("")
            .toInt(2)

        return index in litIndexes
    }

}