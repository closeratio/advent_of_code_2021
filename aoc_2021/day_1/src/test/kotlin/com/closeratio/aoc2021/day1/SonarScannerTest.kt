package com.closeratio.aoc2021.day1

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class SonarScannerTest {

    private val scanner = SonarScanner()
    private val depthValues = javaClass
        .getResource("/test_input.txt")!!
        .readText()
        .trim()
        .split("\n")
        .map(String::trim)
        .map(String::toLong)

    @Test
    fun computeDepthCount() {
        val result = scanner.computeDepthCount(depthValues)
        assertThat(result, `is`(DepthCount(263, 7)))
    }

    @Test
    fun computeWindowedDepthCount() {
        val result = scanner.computeWindowedDepthCount(depthValues)
        assertThat(result, `is`(DepthCount(792, 5)))
    }

}
