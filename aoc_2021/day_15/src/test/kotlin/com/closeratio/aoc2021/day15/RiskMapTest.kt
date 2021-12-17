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
        assertThat(riskMap.map, aMapWithSize(100))
        assertThat(riskMap.map.getValue(Vec2i.ZERO), `is`(1))
        assertThat(riskMap.map.getValue(Vec2i(8, 9)), `is`(8))
    }

    @Test
    fun calculatePathRisk() {
        assertThat(riskMap.calculatePathRisk(), `is`(40))
    }

}