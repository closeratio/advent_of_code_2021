package com.closeratio.aoc2021.day19

import com.closeratio.aoc2021.common.math.Vec3i

class Scanner(
    val name: String,
    val beacons: Set<Beacon>,
    val permutation: Int
) {

    var position: Vec3i? = null

    // Represents a list of vectors linking each beacon with every other beacon detected
    // by the scanner
    val beaconConstellation: Map<Vec3i, Pair<Beacon, Beacon>> = beacons
        .flatMap { beacon ->
            beacons.filter { it != beacon }
                .map { it.position - beacon.position to (beacon to it) }
        }
        .toMap()

    fun permutations(): List<Scanner> = beacons
        .map(Beacon::permutations)
        .let { beaconPermutations ->
            IntRange(0, beaconPermutations.first().size - 1)
                .map { index ->
                    beaconPermutations.map { it[index] }.toSet()
                }
                .mapIndexed { index, beacons -> Scanner(name, beacons, index) }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Scanner) return false

        if (name != other.name) return false
        if (permutation != other.permutation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + permutation
        return result
    }

    override fun toString(): String {
        return "Scanner(name='$name')"
    }

}

