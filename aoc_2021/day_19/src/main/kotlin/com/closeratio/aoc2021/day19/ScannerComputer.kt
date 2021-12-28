package com.closeratio.aoc2021.day19

import com.closeratio.aoc2021.common.math.Vec3i

class ScannerComputer(
    val scanners: List<Scanner>
) {

    data class Result(
        val scanners: List<Scanner>,
        val beacons: Set<Beacon>,
        val largestManhattanDistance: Int
    )

    companion object {
        private const val MIN_MATCHING_BEACONS = 12

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
                        .toSet(),
                    0
                )
            }
            .let(::ScannerComputer)
    }

    private fun computeValidScannerPermutations(): List<Scanner> {
        val referenceScanner = scanners.first()
        referenceScanner.position = Vec3i.ZERO

        val resolvedScanners: MutableList<Scanner> = mutableListOf(referenceScanner)
        val unresolvedScanners = scanners.drop(1).toMutableList()

        while (unresolvedScanners.isNotEmpty()) {
            unresolvedScanners
                .mapNotNull { scanner ->
                    val validPermutation = scanner
                        .permutations()
                        .firstOrNull { permutation -> scannerPermutationValid(resolvedScanners, permutation) }

                    if (validPermutation != null) {
                        scanner to validPermutation
                    } else {
                        null
                    }
                }
                .forEach { (scanner, permutation) ->
                    computeScannerPosition(resolvedScanners, permutation)

                    unresolvedScanners.remove(scanner)
                    resolvedScanners.add(permutation)
                }
        }

        return resolvedScanners
    }

    private fun scannerPermutationValid(
        resolvedScanners: List<Scanner>,
        candidate: Scanner
    ): Boolean = resolvedScanners
        .map { it.beaconConstellation.keys.intersect(candidate.beaconConstellation.keys).size }
        .any { it >= (MIN_MATCHING_BEACONS * (MIN_MATCHING_BEACONS - 1)) }

    private fun computeScannerPosition(
        resolvedScanners: List<Scanner>,
        scanner: Scanner
    ) {
        // Find the scanner to use as a reference position
        val (referenceScanner, intersection) = resolvedScanners
            .map { it to it.beaconConstellation.keys.intersect(scanner.beaconConstellation.keys) }
            .first { it.second.size >= (MIN_MATCHING_BEACONS * (MIN_MATCHING_BEACONS - 1)) }

        // Find the beacon to use as a reference between the two scanners
        val referenceScannerBeacon = referenceScanner.beaconConstellation.getValue(intersection.first()).first
        val resolvedScannerBeacon = scanner.beaconConstellation.getValue(intersection.first()).first

        // Find the delta between the two scanners
        val delta = referenceScannerBeacon.position + resolvedScannerBeacon.position.invert()
        scanner.position = referenceScanner.position!! + delta
    }

    fun processScannerData(): Result {
        val resolvedScanners: List<Scanner> = computeValidScannerPermutations()

        val beacons: Set<Beacon> = resolvedScanners
            .flatMap { scanner -> scanner.beacons.map { it.offset(scanner.position!!) } }
            .toSet()

        val manhattanDistances: List<Int> = resolvedScanners
            .flatMap { scanner ->
                resolvedScanners
                    .filter { it != scanner }
                    .map { (scanner.position!! - it.position!!).manhattanDistance() }
            }

        return Result(
            resolvedScanners,
            beacons,
            manhattanDistances.maxByOrNull { it }!!
        )
    }

}