package com.closeratio.aoc2021.day7

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsIterableContainingInOrder
import org.junit.jupiter.api.Test

class CrabModelTest {

    private val model = CrabModel(javaClass.getResource("/test_input.txt")!!.readText())

    @Test
    fun parsesCorrectly() {
        assertThat(model.crabs, hasSize(10))
        assertThat(model.crabs, IsIterableContainingInOrder.contains(
            Crab(16),
            Crab(1),
            Crab(2),
            Crab(0),
            Crab(4),
            Crab(2),
            Crab(7),
            Crab(1),
            Crab(2),
            Crab(14)
        ))
    }

    @Test
    fun calculateFuelForAlignmentSimple() {
        val result = model.calculateFuelForAlignmentSimple()

        assertThat(result, `is`(37))
    }

    @Test
    fun calculateFuelForAlignmentComplex() {
        val result = model.calculateFuelForAlignmentComplex()

        assertThat(result, `is`(168))
    }

}
