package com.closeratio.aoc2021.day11

import com.closeratio.aoc2021.common.math.Vec2i

class Octopus(
    val position: Vec2i,
    var energyLevel: Int
) {

    var flashed: Boolean = false
        private set
    var flashCount: Int = 0
        private set

    fun increaseEnergyLevel() {
        energyLevel++
    }

    fun flashIfApplicable(
        octopusMap: Map<Vec2i, Octopus>
    ) {
        if (energyLevel <= 9 || flashed) {
            return
        }

        flashed = true
        flashCount++
        (position.adjacent() + position.diagonallyAdjacent())
            .mapNotNull { octopusMap[it] }
            .forEach {
                it.increaseEnergyLevel()
                it.flashIfApplicable(octopusMap)
            }
    }

    fun reset() {
        if (energyLevel > 9) {
            energyLevel = 0
            flashed = false
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Octopus) return false

        if (position != other.position) return false

        return true
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }

    override fun toString(): String {
        return "Octopus(position=$position, energyLevel=$energyLevel)"
    }

}