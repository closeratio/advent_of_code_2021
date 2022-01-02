package com.closeratio.aoc2021.day22

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.junit.jupiter.api.Test

class RegionProcessorTest {

    @Test
    fun parse() {
        val processor = RegionProcessor.parse(javaClass.getResource("/test_input_1.txt")!!.readText())

        assertThat(processor.regions, hasSize(4))
        assertThat(
            processor.regions, contains(
                Region(Cuboid(10..12, 10..12, 10..12), true),
                Region(Cuboid(11..13, 11..13, 11..13), true),
                Region(Cuboid(9..11, 9..11, 9..11), false),
                Region(Cuboid(10..10, 10..10, 10..10), true)
            )
        )
    }

    @Test
    fun processInstructionsSimple() {
        val processor = RegionProcessor.parse(javaClass.getResource("/test_input_1.txt")!!.readText())
        val result = processor.processInstructions(Cuboid(-50..50, -50..50, -50..50))

        assertThat(result, `is`(39))
    }

    @Test
    fun processInstructionsComplex() {
        val processor = RegionProcessor.parse(javaClass.getResource("/test_input_2.txt")!!.readText())
        val result = processor.processInstructions(Cuboid(-50..50, -50..50, -50..50))

        assertThat(result, `is`(590784))
    }

    @Test
    fun processInstructionsComplexFullList() {
        val processor = RegionProcessor.parse(javaClass.getResource("/test_input_3.txt")!!.readText())
        val result = processor.processInstructions()

        assertThat(result, `is`(2758514936282235))
    }

}