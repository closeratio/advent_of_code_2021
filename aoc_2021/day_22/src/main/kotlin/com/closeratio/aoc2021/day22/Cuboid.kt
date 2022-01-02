package com.closeratio.aoc2021.day22

data class Cuboid(
    val xRange: IntRange,
    val yRange: IntRange,
    val zRange: IntRange
) {
    val pointCount = listOf(
        (xRange.last + 1 - xRange.first).toLong(),
        (yRange.last + 1 - yRange.first).toLong(),
        (zRange.last + 1 - zRange.first).toLong()
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
                    lowerX..upperX,
                    lowerY..upperY,
                    lowerZ..upperZ
                )
            }
    }

    fun overlapCuboid(other: Cuboid): Cuboid? {

        val xIntersect: IntRange = xRange.intersection(other.xRange)
        val yIntersect: IntRange = yRange.intersection(other.yRange)
        val zIntersect: IntRange = zRange.intersection(other.zRange)

        if (xIntersect.isEmpty() || yIntersect.isEmpty() || zIntersect.isEmpty()) {
            return null
        }

        return Cuboid(
            xIntersect,
            yIntersect,
            zIntersect
        )
    }

}
