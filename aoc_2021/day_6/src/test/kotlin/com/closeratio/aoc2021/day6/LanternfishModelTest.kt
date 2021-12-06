package com.closeratio.aoc2021.day6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LanternfishModelTest {

    private val model = LanternfishModel(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parsesCorrectly() {
        assertThat(model.initialState, aMapWithSize(4))

        assertThat(model.initialState, hasEntry(TimerValue(1), FishCount(1)))
        assertThat(model.initialState, hasEntry(TimerValue(2), FishCount(1)))
        assertThat(model.initialState, hasEntry(TimerValue(3), FishCount(2)))
        assertThat(model.initialState, hasEntry(TimerValue(4), FishCount(1)))
    }

    companion object {
        @JvmStatic
        fun iterateArguments(): List<Arguments> = listOf(
            Arguments.of(1, 5),
            Arguments.of(2, 6),
            Arguments.of(3, 7),
            Arguments.of(4, 9),
            Arguments.of(5, 10),
            Arguments.of(8, 10),
            Arguments.of(9, 11),
            Arguments.of(18, 26),
            Arguments.of(80, 5934),
            Arguments.of(256, 26984457539L)
        )
    }

    @ParameterizedTest
    @MethodSource("iterateArguments")
    fun iterate(
        days: Int,
        expectedCount: Long
    ) {
        val result = model.iterate(days)
        assertThat(result, `is`(expectedCount))
    }

}
