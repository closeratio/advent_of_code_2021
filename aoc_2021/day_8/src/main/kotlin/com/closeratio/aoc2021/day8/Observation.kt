package com.closeratio.aoc2021.day8

data class Observation(
    val patterns: List<SignalPattern>,
    val outputValues: List<SignalPattern>
) {

    companion object {
        fun parse(input: String): List<Observation> = input
            .trim()
            .split("\n")
            .map(String::trim)
            .map { line ->
                val (left, right) = line.split("|")
                left.trim() to right.trim()
            }
            .map { (left, right) ->
                Observation(
                    left.split(" ").map { SignalPattern.parse(it) },
                    right.split(" ").map { SignalPattern.parse(it) }
                )
            }
    }

    fun countOutputEasyNumbers(): Int = outputValues
        .filter { it.determineSimpleDigit() != null }
        .size

    fun computeMappings(): List<SignalPatternDigitMapping> {
        val knownMappings = hashMapOf(
            Digit(1) to patterns.first { it.signals.size == 2 },
            Digit(7) to patterns.first { it.signals.size == 3 },
            Digit(4) to patterns.first { it.signals.size == 4 },
            Digit(8) to patterns.first { it.signals.size == 7 }
        )

        val fiveSegmentCandidates = patterns.filter { it.signals.size == 5 }
        val sixSegmentCandidates = patterns.filter { it.signals.size == 6 }

        // Work out 6
        val sixPattern = sixSegmentCandidates.first {
            (knownMappings.getValue(Digit(1)).signals - it.signals).isNotEmpty()
        }
        knownMappings[Digit(6)] = sixPattern

        // Work out 9
        val ninePattern = sixSegmentCandidates
            .filter { it != sixPattern }
            .first {
                (knownMappings.getValue(Digit(4)).signals - it.signals).isEmpty()
            }
        knownMappings[Digit(9)] = ninePattern

        // Work out 0
        val zeroPattern = sixSegmentCandidates.first { it != sixPattern && it != ninePattern }
        knownMappings[Digit(0)] = zeroPattern

        // Work out 3
        val threePattern = fiveSegmentCandidates
            .first {
                (knownMappings.getValue(Digit(1)).signals - it.signals).isEmpty()
            }
        knownMappings[Digit(3)] = threePattern

        // Work out 5
        val fivePattern = fiveSegmentCandidates
            .filter { it != threePattern }
            .first {
                (it.signals - knownMappings.getValue(Digit(9)).signals).isEmpty()
            }
        knownMappings[Digit(5)] = fivePattern

        // Work out 2
        val twoPattern = fiveSegmentCandidates.first { it != threePattern && it != fivePattern }
        knownMappings[Digit(2)] = twoPattern

        return knownMappings.entries.map { (k, v) -> SignalPatternDigitMapping(v, k) }
    }

    fun computeOutputValue(): Long {
        val mappings = computeMappings()
            .associateBy(SignalPatternDigitMapping::pattern, SignalPatternDigitMapping::digit)

        return outputValues
            .map(mappings::getValue)
            .map(Digit::value)
            .joinToString("", transform = Int::toString)
            .toLong()
    }

}
