package com.closeratio.aoc2021.day18

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HomeworkTest {

    companion object {
        @JvmStatic
        fun sumMagnitudeArguments(): List<Arguments> = listOf(
            Arguments.of("[[1,2],[[3,4],5]]", 143),
            Arguments.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", 1384),
            Arguments.of("[[[[1,1],[2,2]],[3,3]],[4,4]]", 445),
            Arguments.of("[[[[3,0],[5,3]],[4,4]],[5,5]]", 791),
            Arguments.of("[[[[5,0],[7,4]],[5,5]],[6,6]]", 1137),
            Arguments.of("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", 3488)
        )
    }

    @ParameterizedTest
    @MethodSource("sumMagnitudeArguments")
    fun sumMagnitude(
        input: String,
        expectedValue: Long
    ) {
        val homework = Homework.parse(input)
        val result = homework.sumMagnitude()

        MatcherAssert.assertThat(result, `is`(expectedValue))
    }

}