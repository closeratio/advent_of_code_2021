package com.closeratio.aoc2021.day19

import com.closeratio.aoc2021.common.math.Vec3i

class Scanner(
    val name: String,
    val beacons: Set<Beacon>
) {

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
                .mapIndexed { index, beacons -> Scanner(name + " (Permutation ${index + 1})", beacons) }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Scanner) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Scanner(name='$name')"
    }

}

