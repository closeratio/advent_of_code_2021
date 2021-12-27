package com.closeratio.aoc2021.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
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
    fun beaconCount() {
        assertThat(computer.beaconCount(), `is`(79))
    }

}