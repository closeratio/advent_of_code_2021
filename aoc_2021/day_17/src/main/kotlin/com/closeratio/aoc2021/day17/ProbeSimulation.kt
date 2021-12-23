package com.closeratio.aoc2021.day17

import com.closeratio.aoc2021.common.math.Vec2i
import kotlin.math.absoluteValue

class ProbeSimulation(
    val targetArea: TargetArea
) {

    companion object {
        fun parse(targetAreaString: String): ProbeSimulation = targetAreaString
            .trim()
            .replace("target area: ", "")
            .split(",")
            .map(String::trim)
            .map { it.drop(2) }
            .map { it.split("..") }
            .map { it.map { element -> element.toInt() } }
            .let { (xRange, yRange) ->
                ProbeSimulation(
                    TargetArea(
                        xRange[0]..xRange[1],
                        yRange[0].. yRange[1]
                    )
                )
            }
    }

    fun calculateMaxY(): Int {
        return IntRange(1, targetArea.yRange.first.absoluteValue - 1).sum()
    }

    fun calculateVelocityPermutationCount(): Int {
        val minY = targetArea.yRange.first
        val maxY = calculateMaxY()

        val minX = calculateMinX()
        val maxX = targetArea.xRange.last

        val validPermutations = IntRange(minY, maxY)
            .flatMap { y ->
                IntRange(minX, maxX).map { x ->
                    Probe(Vec2i.ZERO, Vec2i(x, y))
                }
            }
            .filter(::isValidVelocity)

        return validPermutations.size
    }

    private fun isValidVelocity(probe: Probe): Boolean {
        val states = mutableListOf(probe)

        while (true) {
            val nextState = states.last().step()

            if (nextState.position.x > targetArea.xRange.last || nextState.position.y < targetArea.yRange.first) {
                return false
            } else if (targetArea.contains(nextState.position)) {
                return true
            } else {
                states.add(nextState)
            }
        }
    }

    private fun calculateMinX(): Int {
        val targetAreaStart = targetArea.xRange.first

        var xCandidate = 0
        var found = false
        while (!found) {
            ++xCandidate
            val maxX = IntRange(1, xCandidate).sum()
            if (maxX > targetAreaStart) {
                found = true
            }
        }

        return xCandidate
    }

}