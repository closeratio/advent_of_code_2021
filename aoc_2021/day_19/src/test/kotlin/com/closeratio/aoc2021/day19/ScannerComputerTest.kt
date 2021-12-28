package com.closeratio.aoc2021.day19

import com.closeratio.aoc2021.common.math.Vec3i
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInAnyOrder
import org.hamcrest.collection.IsMapContaining.hasEntry
import org.hamcrest.collection.IsMapWithSize.aMapWithSize
import org.junit.jupiter.api.Test

class ScannerComputerTest {

    private val computer = ScannerComputer.parse(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parse() {
        assertThat(computer.scanners, hasSize(5))

        val beaconCounts = computer
            .scanners
            .map { it.beacons.size }
            .groupBy { it }
            .mapValues { (_, v) -> v.size }

        assertThat(beaconCounts, aMapWithSize(2))
        assertThat(beaconCounts, hasEntry(25, 3))
        assertThat(beaconCounts, hasEntry(26, 2))
    }

    @Test
    fun processScannerData() {
        val result = computer.processScannerData()

        assertThat(result.scanners.map { it.name to it.position }, IsIterableContainingInAnyOrder.containsInAnyOrder(
            "scanner 0" to Vec3i.ZERO,
            "scanner 1" to Vec3i(68, -1246, -43),
            "scanner 2" to Vec3i(1105, -1205, 1229),
            "scanner 3" to Vec3i(-92, -2380, -20),
            "scanner 4" to Vec3i(-20, -1133, 1061)
        ))
        assertThat(result.beacons.size, `is`(79))
        assertThat(result.largestManhattanDistance, `is`(3621))
    }

}