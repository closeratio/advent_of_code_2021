package com.closeratio.aoc2021.day9

import com.closeratio.aoc2021.common.math.Vec2i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test

class HeightmapTest {

    private val heightMap = Heightmap.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(heightMap.map, aMapWithSize(50))
        assertThat(heightMap.map[Vec2i(0, 0)], `is`(2))
        assertThat(heightMap.map[Vec2i(1, 0)], `is`(1))
        assertThat(heightMap.map[Vec2i(2, 1)], `is`(8))
    }

    @Test
    fun riskLevelSum() {
        val result = heightMap.riskLevelSum()
        assertThat(result, `is`(15))
    }

    @Test
    fun findBasins() {
        val result = heightMap.findBasins().sortedBy { it.positions.size }

        assertThat(result, hasSize(4))
        assertThat(result[0].positions, hasSize(3))
        assertThat(result[1].positions, hasSize(9))
        assertThat(result[2].positions, hasSize(9))
        assertThat(result[3].positions, hasSize(14))
    }

    @Test
    fun basinFactor() {
        val result = heightMap.basinFactor()
        assertThat(result, `is`(1134))
    }

}