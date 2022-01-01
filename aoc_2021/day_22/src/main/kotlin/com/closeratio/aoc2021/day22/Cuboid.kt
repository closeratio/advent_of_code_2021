package com.closeratio.aoc2021.day22

import com.closeratio.aoc2021.common.math.Vec3i

data class Cuboid(
    val lowerCorner: Vec3i,
    val upperCorner: Vec3i
) {
    val pointCount = listOf(
        (upperCorner.x + 1 - lowerCorner.x).toLong(),
        (upperCorner.y + 1 - lowerCorner.y).toLong(),
        (upperCorner.z + 1 - lowerCorner.z).toLong()
    ).reduce { acc, curr -> acc * curr }

    companion object {
        fun parse(cubeString: String): Cuboid = cubeString
            .trim()
            .split(",")
            .map { it.split("=").last() }
            .let { (xRange, yRange, zRange) ->
                val (lowerX, upperX) = xRange.split("..").map(String::toInt)
                val (lowerY, upperY) = yRange.split("..").map(String::toInt)
                val (lowerZ, upperZ) = zRange.split("..").map(String::toInt)

                Cuboid(
                    Vec3i(lowerX, lowerY, lowerZ),
                    Vec3i(upperX, upperY, upperZ)
                )
            }
    }

    private fun ranges(): Triple<IntRange, IntRange, IntRange> = Triple(
        lowerCorner.x..upperCorner.x,
        lowerCorner.y..upperCorner.y,
        lowerCorner.z..upperCorner.z
    )

    fun overlapCuboid(other: Cuboid): Cuboid? {
        val (xRange, yRange, zRange) = ranges()
        val (otherXRange, otherYRange, otherZRange) = other.ranges()

        val xIntersect = xRange.intersect(otherXRange)
        val yIntersect = yRange.intersect(otherYRange)
        val zIntersect = zRange.intersect(otherZRange)

        if (xIntersect.isEmpty() || yIntersect.isEmpty() || zIntersect.isEmpty()) {
            return null
        }

        return Cuboid(
            Vec3i(xIntersect.minOf { it }, yIntersect.minOf { it }, zIntersect.minOf { it }),
            Vec3i(xIntersect.maxOf { it }, yIntersect.maxOf { it }, zIntersect.maxOf { it })
        )
    }

}
