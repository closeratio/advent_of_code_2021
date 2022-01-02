package com.closeratio.aoc2021.day22

data class Region(
    val volume: Cuboid,
    val on: Boolean
) {

    companion object {
        fun parse(line: String): Region = line
            .trim()
            .split(" ")
            .let { (typeString, cubeString) ->
                Region(
                    Cuboid.parse(cubeString),
                    typeString == "on"
                )
            }
    }

    fun combine(other: Region): Region? {
        val intersection = volume.overlapCuboid(other.volume) ?: return null

        return Region(
            intersection,
            !on
        )
    }

    fun onCount(): Long = volume.pointCount * if (on) 1 else -1

}
