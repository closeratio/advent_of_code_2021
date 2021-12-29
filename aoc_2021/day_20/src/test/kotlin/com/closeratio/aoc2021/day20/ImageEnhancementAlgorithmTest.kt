package com.closeratio.aoc2021.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class ImageEnhancementAlgorithmTest {

    private val imageEnhancementAlgorithm = javaClass
        .getResource("/test_input.txt")!!
        .readText()
        .trim()
        .split("\n\n")
        .first()
        .let(::ImageEnhancementAlgorithm)

    @Test
    fun runAlgorithm() {
        // 33
        assertThat(
            imageEnhancementAlgorithm.runAlgorithm(
                listOf(
                    false, false, false, true, false, false, false, false, true
                )
            ),
            `is`(false)
        )

        // 34
        assertThat(
            imageEnhancementAlgorithm.runAlgorithm(
                listOf(
                    false, false, false, true, false, false, false, true, false
                )
            ),
            `is`(true)
        )

        // 35
        assertThat(
            imageEnhancementAlgorithm.runAlgorithm(
                listOf(
                    false, false, false, true, false, false, false, true, true
                )
            ),
            `is`(true)
        )

        // 36
        assertThat(
            imageEnhancementAlgorithm.runAlgorithm(
                listOf(
                    false, false, false, true, false, false, true, false, false
                )
            ),
            `is`(false)
        )

    }

}