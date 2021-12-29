package com.closeratio.aoc2021.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class FloorMapperTest {

    private val mapper = FloorMapper.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(mapper.initialImage.litPixels(), hasSize(10))
    }

    @Test
    fun applyAlgorithm() {
        val result = mapper.applyAlgorithm(2)
        assertThat(result.litPixels, hasSize(35))
    }

}