package com.closeratio.aoc2021.day19

import com.closeratio.aoc2021.common.math.Vec3i

data class Beacon(
    val position: Vec3i
) {
    fun permutations(): List<Beacon> {
        val permutations = position.permutations().map(::Beacon)
        assert(permutations.size == 24)
        return permutations
    }

    fun offset(amount: Vec3i): Beacon = Beacon(position + amount)
}