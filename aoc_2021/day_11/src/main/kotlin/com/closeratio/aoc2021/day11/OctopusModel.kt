package com.closeratio.aoc2021.day11

import com.closeratio.aoc2021.common.math.Vec2i

class OctopusModel(
    val octopusMap: Map<Vec2i, Octopus>
) {

    private var allFlashed = false

    companion object {
        fun parse(input: String): OctopusModel = input
            .trim()
            .split("\n")
            .map(String::trim)
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char ->
                    Octopus(Vec2i(x, y), char.toString().toInt())
                }
            }
            .associateBy(Octopus::position)
            .let(::OctopusModel)
    }

    fun tick() {
        octopusMap.values.forEach(Octopus::increaseEnergyLevel)
        octopusMap.values.forEach { it.flashIfApplicable(octopusMap) }
        allFlashed = octopusMap.values.all(Octopus::flashed)
        octopusMap.values.forEach(Octopus::reset)
    }

    fun print(): String = IntRange(0, octopusMap.keys.maxOf { it.y })
        .joinToString("\n") { y ->
            IntRange(0, octopusMap.keys.maxOf { it.x })
                .joinToString("") { x ->
                    octopusMap.getValue(Vec2i(x, y)).energyLevel.toString()
                }
        }

    fun iterateUntilAllFlash(): Int {
        var step = 0
        while (!allFlashed) {
            tick()
            step++
        }

        return step
    }

    fun flashCount(): Int = octopusMap
        .values
        .sumOf(Octopus::flashCount)

}