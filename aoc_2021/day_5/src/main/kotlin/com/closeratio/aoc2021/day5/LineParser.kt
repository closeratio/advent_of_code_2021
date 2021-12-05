package com.closeratio.aoc2021.day5

import com.closeratio.aoc2021.common.math.Line
import com.closeratio.aoc2021.common.math.Vec2i

class LineParser {

    fun parseLines(input: String): List<Line> = input
        .trim()
        .split("\n")
        .map(String::trim)
        .map { line ->
            val (firstVec, secondVec) = line
                .split("->")
                .map { vecString -> vecString.split(",") }
                .map { (xString, yString) -> Vec2i(xString.trim().toInt(), yString.trim().toInt()) }

            Line(firstVec, secondVec)
        }

}