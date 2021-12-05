package com.closeratio.aoc2021.day5

import com.closeratio.aoc2021.common.math.Line

class LineAnalyser {

    private val parser = LineParser()

    fun getOverlappingPointCount(
        input: String,
        ignoreDiagonals: Boolean
    ): Int {
        val lines = parser.parseLines(input)

        return lines
            .filter {
                if (ignoreDiagonals) {
                    !it.isDiagonal()
                } else {
                    true
                }
            }
            .flatMap(Line::points)
            .groupBy { it }
            .filterValues { it.size > 1 }
            .size
    }

}