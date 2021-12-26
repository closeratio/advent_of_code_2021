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

    fun sumMagnitude(): PairNode {
        reduce(lines.first())

        val result = lines
            .drop(1)
            .fold(lines.first()) { acc, curr ->

                PairNode(
                    acc,
                    curr
                ).also(::reduce)
            }

        return result
    }

    fun reduce(root: PairNode) {
        var candidatesExist = true
        while (candidatesExist) {
            val explodeCandidates = root.explodeCandidates(0)
            val splitCandidates = root.splitCandidates()

            if (explodeCandidates.isNotEmpty()) {
                explode(root)
            } else if (splitCandidates.isNotEmpty()) {
                split(root)
            }

            candidatesExist = explodeCandidates.isNotEmpty() || splitCandidates.isNotEmpty()
        }
    }

    fun explode(root: PairNode) {
        val candidate = root.explodeCandidates(0).first()
        val parentMap = root.buildParentMap()
        val parent = parentMap[candidate]

        // Find the first regular number to the left, if there is one, and add the candidate's left number to it
        val leftTarget = parent?.searchLeft(parentMap, setOf(candidate.searchId))
        if (leftTarget != null) {
            leftTarget.value += (candidate.left as LiteralNode).value
        }

        // Then find the first regular number to the right, if there is one, and add the candidate's right number to it
        val rightTarget = parent?.searchRight(parentMap, setOf(candidate.searchId))
        if (rightTarget != null) {
            rightTarget.value += (candidate.right as LiteralNode).value
        }

        // Replace the candidate with a zero
        if (parent != null) {
            val replacement = LiteralNode(0)
            if (parent.left === candidate) {
                parent.left = replacement
            } else {
                parent.right = replacement
            }
        }
    }

    fun split(root: PairNode) {
        val candidate = root.splitCandidates().first()
        val parentMap = root.buildParentMap()
        val parent = parentMap.getValue(candidate)

        val replacement = PairNode(
            LiteralNode(
                candidate.value / 2
            ),
            LiteralNode(
                when (candidate.value % 2) {
                    0L -> candidate.value / 2
                    else -> (candidate.value / 2) + 1
                }
            )
        )

        if (parent.left == candidate) {
            parent.left = replacement
        } else {
            parent.right = replacement
        }
    }

}