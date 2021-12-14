package com.closeratio.aoc2021.day13

import com.closeratio.aoc2021.common.math.Vec2i
import com.closeratio.aoc2021.day13.FoldInstruction.Direction.HORIZONTAL
import com.closeratio.aoc2021.day13.FoldInstruction.Direction.VERTICAL

data class Manual(
    val dots: Set<Vec2i>,
    val foldInstructions: List<FoldInstruction>
) {

    companion object {
        fun parse(input: String): Manual = input
            .trim()
            .split("\n\n")
            .let { (firstBlock, secondBlock) ->
                Manual(
                    parseDots(firstBlock),
                    parseFoldInstructions(secondBlock)
                )
            }

        private fun parseDots(input: String): Set<Vec2i> = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map { line -> line.split(",") }
            .map { (x, y) -> Vec2i(x.toInt(), y.toInt()) }
            .toSet()

        private fun parseFoldInstructions(input: String): List<FoldInstruction> = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map { it.split(" ").last() }
            .map { it.split("=") }
            .map { (direction, amount) ->
                FoldInstruction(
                    when (direction) {
                        "y" -> HORIZONTAL
                        "x" -> VERTICAL
                        else -> throw IllegalArgumentException("Unknown direction: $direction")
                    },
                    amount.toInt()
                )
            }
    }

    fun fold(
        instruction: FoldInstruction,
        currentDots: Set<Vec2i>
    ): Result {
        val (direction, amount) = instruction
        val result = currentDots
            .map { dot ->
                when {
                    direction == VERTICAL && dot.x > amount -> {
                        Vec2i(
                            dot.x - (dot.x - amount) * 2,
                            dot.y
                        )
                    }
                    direction == HORIZONTAL && dot.y > amount -> {
                        Vec2i(
                            dot.x,
                            dot.y - (dot.y - amount) * 2
                        )
                    }
                    else -> dot
                }
            }
            .toSet()

        val minX = result.minOf(Vec2i::x)
        val maxX = result.maxOf(Vec2i::x)
        val minY = result.minOf(Vec2i::y)
        val maxY = result.maxOf(Vec2i::y)

        return Result(
            result.size,
            IntRange(minY, maxY)
                .joinToString("\n") { y ->
                    IntRange(minX, maxX)
                        .joinToString("") { x ->
                            if (Vec2i(x, y) in result) "#" else "."
                        }
                },
            result
        )
    }

    fun executeInstructions(): Result = foldInstructions
        .fold(Result(dots.size, "", dots)) { acc, curr ->
            fold(curr, acc.updatedDots)
        }

    data class Result(
        val visibleDotCount: Int,
        val printed: String,
        val updatedDots: Set<Vec2i>
    )

}