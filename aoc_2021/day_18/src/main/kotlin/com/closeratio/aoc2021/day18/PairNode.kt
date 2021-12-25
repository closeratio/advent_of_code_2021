package com.closeratio.aoc2021.day18

import com.closeratio.aoc2021.common.text.CharBuffer

class PairNode(
    nestLevel: Int,
    var left: Node,
    var right: Node
): Node(nestLevel) {

    companion object {
        fun parse(input: String): PairNode = parse(0, CharBuffer(input.trim()))

        private fun parse(nestLevel: Int, buffer: CharBuffer): PairNode {

            assert(buffer.remove(1).single() == '[')
            val leftNode = parseNode(nestLevel, buffer)
            assert(buffer.remove(1).single() == ',')
            val rightNode = parseNode(nestLevel, buffer)
            assert(buffer.remove(1).single() == ']')

            val pair = PairNode(nestLevel, leftNode, rightNode)
            leftNode.parent = pair
            rightNode.parent = pair

            return pair
        }

        private fun parseNode(nestLevel: Int, buffer: CharBuffer): Node = when (buffer.peek(1).single()) {
            '[' -> parse(nestLevel + 1, buffer)
            else -> LiteralNode(nestLevel + 1, buffer.remove(1).single().toString().toLong())
        }
    }

    fun explodeCandidates(): List<PairNode> = if (nestLevel >= 4) {
        listOf(this)
    } else {
        val leftCandidates = (left as? PairNode)?.explodeCandidates() ?: emptyList()
        val rightCandidates = (right as? PairNode)?.explodeCandidates() ?: emptyList()
        leftCandidates + rightCandidates
    }

    fun splitCandidates(): List<LiteralNode> = specificSplitCandidates(left) + specificSplitCandidates(right)

    private fun specificSplitCandidates(
        node: Node
    ): List<LiteralNode> = if (node is LiteralNode) {
        if (node.value >= 10) {
            listOf(node)
        } else {
            emptyList()
        }
    } else {
        (left as PairNode).splitCandidates()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PairNode) return false
        if (!super.equals(other)) return false

        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    override fun toString(): String {
        return "[$left,$right]"
    }

}