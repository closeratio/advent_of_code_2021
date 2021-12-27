package com.closeratio.aoc2021.common.math

import kotlin.math.pow

data class Vec3i(
    val x: Int,
    val y: Int,
    val z: Int
) {

    companion object {
        val ZERO = Vec3i(0, 0, 0)
    }

    fun length(): Double = (x.toDouble().pow(2) + y.toDouble().pow(2) + z.toDouble().pow(2)).pow(0.5)

    operator fun plus(other: Vec3i): Vec3i = Vec3i(x + other.x, y + other.y, z + other.z)
    operator fun minus(other: Vec3i): Vec3i = Vec3i(x - other.x, y - other.y, z - other.z)
    operator fun times(other: Vec3i): Vec3i = Vec3i(x * other.x, y * other.y, z * other.z)

    private fun rotateX(): Vec3i = Vec3i(
        x, -z, y
    )

    private fun rotateY(): Vec3i = Vec3i(
        -z, y, x
    )

    private fun rotateZ(): Vec3i = Vec3i(
        -y, x, z
    )

    fun permutations(): Set<Vec3i> = setOf(
        this,
        rotateX(),
        rotateX().rotateX(),
        rotateX().rotateX().rotateX(),

        rotateY(),
        rotateY().rotateX(),
        rotateY().rotateX().rotateX(),
        rotateY().rotateX().rotateX().rotateX(),

        rotateY().rotateY(),
        rotateY().rotateY().rotateX(),
        rotateY().rotateY().rotateX().rotateX(),
        rotateY().rotateY().rotateX().rotateX().rotateX(),

        rotateY().rotateY().rotateY(),
        rotateY().rotateY().rotateY().rotateX(),
        rotateY().rotateY().rotateY().rotateX().rotateX(),
        rotateY().rotateY().rotateY().rotateX().rotateX().rotateX(),

        rotateZ(),
        rotateZ().rotateX(),
        rotateZ().rotateX().rotateX(),
        rotateZ().rotateX().rotateX().rotateX(),

        rotateZ().rotateZ().rotateZ(),
        rotateZ().rotateZ().rotateZ().rotateX(),
        rotateZ().rotateZ().rotateZ().rotateX().rotateX(),
        rotateZ().rotateZ().rotateZ().rotateX().rotateX().rotateX()
    )

}
