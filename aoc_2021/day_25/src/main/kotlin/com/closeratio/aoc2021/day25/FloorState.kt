package com.closeratio.aoc2021.day25

import com.closeratio.aoc2021.common.math.Vec2i

data class FloorState(
    val cucumberMap: Map<Vec2i, Cucumber>,
    val width: Int,
    val height: Int
) {

    companion object {
        fun parse(input: String): FloorState = input
            .trim()
            .split("\n")
            .map(String::trim)
            .flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, char ->
                    when (char) {
                        'v' -> VerticalCucumber(Vec2i(x, y))
                        '>' -> HorizontalCucumber(Vec2i(x, y))
                        else -> null
                    }
                }
            }
            .let { cucumbers ->
                FloorState(
                    cucumbers.associateBy(Cucumber::position),
                    cucumbers.maxOf { it.position.x + 1 },
                    cucumbers.maxOf { it.position.y + 1 }
                )
            }
    }

    fun iterate(): FloorState {
        val intermediateState = FloorState(
            cucumberMap.filterValues { it is VerticalCucumber } + cucumberMap
                .values
                .filterIsInstance<HorizontalCucumber>()
                .map { it.moveIfPossible(this) }
                .associateBy(Cucumber::position),
            width,
            height
        )

        return FloorState(
            intermediateState.cucumberMap.filterValues { it is HorizontalCucumber } + intermediateState
                .cucumberMap
                .values
                .filterIsInstance<VerticalCucumber>()
                .map { it.moveIfPossible(intermediateState) }
                .associateBy(Cucumber::position),
            width,
            height
        )
    }

}