package com.closeratio.aoc2021.day20

import com.closeratio.aoc2021.common.math.Vec2i

data class Image(
    val pixels: Map<Vec2i, Boolean>
) {
    companion object {
        fun parse(input: String): Image = input
            .trim()
            .split("\n")
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char ->
                    Vec2i(x, y) to when (char) {
                        '#' -> true
                        else -> false
                    }
                }
            }
            .toMap()
            .let(::Image)
    }

    fun litPixels(): Set<Vec2i> = pixels
        .filterValues { it }
        .keys

}
