package com.closeratio.aoc2021.day20

import com.closeratio.aoc2021.common.math.Vec2i

data class Image(
    val litPixels: Set<Vec2i>
) {
    companion object {
        fun parse(input: String): Image = input
            .trim()
            .split("\n")
            .flatMapIndexed { y, line ->
                line.mapIndexed { x, char -> x to char }
                    .filter { (_, c) -> c == '#' }
                    .map { (x, _) -> Vec2i(x, y) }
            }
            .toSet()
            .let(::Image)
    }

    fun print(): String = IntRange(litPixels.minOf(Vec2i::y), litPixels.maxOf(Vec2i::y))
        .joinToString("\n") { y ->
            IntRange(litPixels.minOf(Vec2i::x), litPixels.maxOf(Vec2i::x))
                .joinToString("") { x ->
                    when {
                        Vec2i(x, y) in litPixels -> "#"
                        else -> "."
                    }
                }
        }


}
