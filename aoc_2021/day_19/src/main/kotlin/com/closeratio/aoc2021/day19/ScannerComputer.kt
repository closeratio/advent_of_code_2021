package com.closeratio.aoc2021.day19

import com.closeratio.aoc2021.common.math.Vec3i

class ScannerComputer(
    val scanners: List<Scanner>
) {

    companion object {
        fun parse(input: String): ScannerComputer = input
            .trim()
            .split("\n\n")
            .map { it.split("\n") }
            .map { group ->
                Scanner(
                    group.first().replace("---", "").trim(),
                    group.drop(1)
                        .map { it.split(",") }
                        .map { (x, y, z) -> Beacon(Vec3i(x.toInt(), y.toInt(), z.toInt())) }
                        .toSet()
                )
            }
            .let(::ScannerComputer)
    }

    fun beaconCount(): Int {
        val scannerPermutations = scanners
            .drop(1)
            .map(Scanner::permutations)

        // Test code
        val matchingList = scannerPermutations[0]
            .map { scanners[0].beaconConstellation.keys.intersect(it.beaconConstellation.keys) }
            .maxByOrNull(Set<Vec3i>::size)!!
            .map { scanners[0].beaconConstellation.getValue(it).first }
            .toSet()

        return 0
    }

}