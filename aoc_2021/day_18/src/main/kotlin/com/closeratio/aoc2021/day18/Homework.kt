package com.closeratio.aoc2021.day18

class Homework(
    val lines: List<PairNode>
) {

    companion object {
        fun parse(input: String) = input
            .trim()
            .split("\n")
            .map(PairNode.Companion::parse)
            .let(::Homework)
    }

    fun sumMagnitude(): Long {
        val pair = lines.first()

        var candidatesExist = true
        while (candidatesExist) {
            val explodeCandidates = pair.explodeCandidates()
            val splitCandidates = pair.splitCandidates()

            if (explodeCandidates.isNotEmpty()) {
                explode(pair)
            } else if (splitCandidates.isNotEmpty()) {
                split(pair)
            }

            candidatesExist = explodeCandidates.isNotEmpty() || splitCandidates.isNotEmpty()
        }

        return 0
    }

    private fun explode(pair: PairNode) {
        val candidate = pair.explodeCandidates().first()


    }

    private fun split(pair: PairNode) {

    }

}