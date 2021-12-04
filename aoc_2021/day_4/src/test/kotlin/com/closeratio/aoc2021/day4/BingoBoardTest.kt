package com.closeratio.aoc2021.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class BingoBoardTest {

    private val board = BingoBoard(
        """
            22 13 17 11  0
             8  2 23  4 24
            21  9 14 16  7
             6 10  3 18  5
             1 12 20 15 19
        """.trimIndent()
    )

    @Test
    fun parsedCorrectly() {

        assertThat(board.rows, hasSize(5))
        assertThat(
            board.rows, contains(
                setOf(22, 13, 17, 11, 0),
                setOf(8, 2, 23, 4, 24),
                setOf(21, 9, 14, 16, 7),
                setOf(6, 10, 3, 18, 5),
                setOf(1, 12, 20, 15, 19)
            )
        )

        assertThat(board.columns, hasSize(5))
        assertThat(
            board.columns, contains(
                setOf(22, 8, 21, 6, 1),
                setOf(13, 2, 9, 10, 12),
                setOf(17, 23, 14, 3, 20),
                setOf(11, 4, 16, 18, 15),
                setOf(0, 24, 7, 5, 19)
            )
        )
    }

    @Test
    fun playNumbersNoWinner() {
        val result = board.playNumbers(
            setOf(
                7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24
            )
        )

        assertThat(result, `is`(nullValue()))
    }

    @Test
    fun playNumbersWinnerFound() {
        val winnerBoard = BingoBoard(
            """
                14 21 17 24  4
                10 16 15  9 19
                18  8 23 26 20
                22 11 13  6  5
                 2  0 12  3  7
            """.trimIndent()
        )

        val result = winnerBoard.playNumbers(
            setOf(
                7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24
            )
        )

        assertThat(result, `is`(4512))
    }

}