package com.closeratio.aoc2021.day4

import java.lang.IllegalStateException

class BingoGame(
    inputData: String
) {

    val numberSequence = inputData
        .trim()
        .split("\n\n")
        .first()
        .trim()
        .split(",")
        .map(String::trim)
        .map(String::toInt)

    val boards = inputData
        .trim()
        .split("\n\n")
        .drop(1)
        .map(::BingoBoard)

    fun calculateWinningScore(): Int {
        IntRange(1, numberSequence.size)
            .map(numberSequence::take)
            .map(List<Int>::toSet)
            .forEach { currentSequence ->
                val winningScores = boards.mapNotNull { board -> board.playNumbers(currentSequence) }

                if (winningScores.isNotEmpty()) {
                    return winningScores.first()
                }
            }

        throw IllegalStateException("No winning board found")
    }

    fun calculateLastWinningScore(): Int {
        val sequencePairs = IntRange(1, numberSequence.size)
            .map(numberSequence::take)
            .map(List<Int>::toSet)
            .zipWithNext()

        sequencePairs
            .forEach { (firstSequence, secondSequence) ->
                val firstScores = boards
                    .mapIndexed { index, board -> index to board.playNumbers(firstSequence) }
                    .filter { (_, score) -> score != null }
                val secondScores = boards
                    .mapIndexed { index, board -> index to board.playNumbers(secondSequence) }
                    .filter { (_, score) -> score != null }

                if (secondScores.size == boards.size && secondScores.size > firstScores.size) {
                    val firstWinningIndices = firstScores.map(Pair<Int, Int?>::first).toSet()
                    val secondWinningIndices = secondScores.map(Pair<Int, Int?>::first).toSet()

                    val lastWinningIndex = (secondWinningIndices - firstWinningIndices).first()

                    return secondScores[lastWinningIndex].second!!
                }
            }

        throw IllegalStateException("No winning board found")
    }

}