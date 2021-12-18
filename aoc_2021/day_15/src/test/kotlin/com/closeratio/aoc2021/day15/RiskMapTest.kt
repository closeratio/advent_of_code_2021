package com.closeratio.aoc2021.day15

import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test

class RiskMapTest {

    private val riskMap = RiskMap.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(riskMap.template, aMapWithSize(100))
        assertThat(riskMap.template.getValue(Vec2i.ZERO), `is`(1))
        assertThat(riskMap.template.getValue(Vec2i(8, 9)), `is`(8))
    }

    @Test
    fun calculatePathRiskSimple() {
        assertThat(riskMap.calculatePathRisk(), `is`(40))
    }

    @Test
    fun getRiskLevel() {
        val expected = javaClass.getResource("/test_input_expanded.txt")!!.readText().trim()

        val output = IntRange(0, 49).joinToString("\n") { y ->
            IntRange(0, 49).joinToString("") { x ->
                riskMap.getRiskLevel(Vec2i(x, y)).toString()
            }
        }

        assertThat(output, `is`(expected))
    }

    @Test
    fun calculatePathRiskComplex() {
        assertThat(riskMap.calculatePathRisk(
            Vec2i.ZERO,
            Vec2i(49, 49)
        ), `is`(315))
    }

}