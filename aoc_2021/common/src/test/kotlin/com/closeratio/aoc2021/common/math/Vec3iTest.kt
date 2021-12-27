package com.closeratio.aoc2021.common.math

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test

class Vec3iTest {

    private val vec = Vec3i(5, 7, 11)

    @Test
    fun permutations() {
        val permutations = vec.permutations()
        assertThat(permutations, hasSize(24))
    }

}