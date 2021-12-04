package com.closeratio.aoc2021.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class BingoGameTest {

    private val game = BingoGame(
        javaClass
            .getResource("/test_input.txt")!!
            .readText()
    )

    @Test
    fun parsedCorrectly() {
        assertThat(game.numberSequence, hasSize(27))
        assertThat(
            game.numberSequence,
            contains(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)
        )

        val board = game.boards.first()
        assertThat(board.rows, hasSize(5))
        assertThat(
            board.rows,
            contains(
                setOf(22, 13, 17, 11, 0),
                setOf(8, 2, 23, 4, 24),
                setOf(21, 9, 14, 16, 7),
                setOf(6, 10, 3, 18, 5),
                setOf(1, 12, 20, 15, 19)
            )
        )
    }

    @Test
    fun calculateWinningScore() {
        val result = game.calculateWinningScore()

        assertThat(result, `is`(4512))
    }

    @Test
    fun calculateLastWinningScore() {
        val result = game.calculateLastWinningScore()

        assertThat(result, `is`(1924))
    }

}