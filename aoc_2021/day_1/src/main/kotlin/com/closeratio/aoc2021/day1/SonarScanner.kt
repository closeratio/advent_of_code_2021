package com.closeratio.aoc2021.day1

class SonarScanner {

    fun computeDepthCount(
        inputData: List<Long>
    ): DepthCount = inputData
        .drop(1)
        .fold(
            DepthCount(inputData.first(), 0),
        ) { acc, curr ->
            DepthCount(
                curr,
                if (acc.previousReading < curr) acc.increases + 1 else acc.increases
            )
        }

    fun computeWindowedDepthCount(
        inputData: List<Long>
    ): DepthCount = inputData
        .windowed(3)
        .map(List<Long>::sum)
        .let(this::computeDepthCount)

}
