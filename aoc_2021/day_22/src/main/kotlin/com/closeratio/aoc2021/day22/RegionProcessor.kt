package com.closeratio.aoc2021.day22

class RegionProcessor(
    val regions: List<Region>
) {

    companion object {
        fun parse(input: String): RegionProcessor = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map(Region.Companion::parse)
            .let(::RegionProcessor)
    }

    fun processInstructions(
        validCubeVolume: Cuboid? = null
    ): Long {
        return if (validCubeVolume != null) {
            processInstructions(
                regions.mapNotNull {
                    val overlap = it.volume.overlapCuboid(Cuboid(-50..50, -50..50, -50..50))

                    if (overlap != null) {
                        Region(overlap, it.on)
                    } else {
                        null
                    }
                }
            )
        } else {
            processInstructions(regions)
        }
    }

    private fun processInstructions(
        inputRegions: List<Region>
    ): Long {
        val derivedRegions = mutableListOf<Region>()

        inputRegions.forEach { region ->
            derivedRegions.addAll(derivedRegions.mapNotNull { it.combine(region) })

            if (region.onCount() > 0) {
                derivedRegions.add(region)
            }
        }

        return derivedRegions.sumOf(Region::onCount)
    }

}