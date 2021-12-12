package com.closeratio.aoc2021.day12

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CaveSystemTest {

    @Test
    fun parse() {
        val caveSystem = CaveSystem.parse(javaClass.getResource("/test_input_1.txt")!!.readText())
        assertThat(caveSystem.caves, aMapWithSize(6))

        val startCave = caveSystem.caves.getValue("start")
        assertThat(
            startCave.linkedCaves.map(Cave::name), containsInAnyOrder(
                "A",
                "b"
            )
        )
    }

    companion object {
        @JvmStatic
        fun pathCountSimpleArguments(): List<Arguments> = listOf(
            Arguments.of("/test_input_1.txt", 10),
            Arguments.of("/test_input_2.txt", 19),
            Arguments.of("/test_input_3.txt", 226)
        )

        @JvmStatic
        fun pathCountComplexArguments(): List<Arguments> = listOf(
            Arguments.of("/test_input_1.txt", 36),
            Arguments.of("/test_input_2.txt", 103),
            Arguments.of("/test_input_3.txt", 3509)
        )
    }

    @ParameterizedTest
    @MethodSource("pathCountSimpleArguments")
    fun pathCountSimple(
        resourceName: String,
        expectedCount: Int
    ) {
        val caveSystem = CaveSystem.parse(javaClass.getResource(resourceName)!!.readText())
        val result = caveSystem.pathCount()
        assertThat(result, `is`(expectedCount))
    }

    @ParameterizedTest
    @MethodSource("pathCountComplexArguments")
    fun pathCountComplex(
        resourceName: String,
        expectedCount: Int
    ) {
        val caveSystem = CaveSystem.parse(javaClass.getResource(resourceName)!!.readText())
        val result = caveSystem.pathCountComplex()
        assertThat(result, `is`(expectedCount))
    }

}