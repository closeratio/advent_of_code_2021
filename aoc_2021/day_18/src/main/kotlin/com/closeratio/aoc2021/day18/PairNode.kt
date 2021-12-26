package com.closeratio.aoc2021.day18

import com.closeratio.aoc2021.common.text.CharBuffer
import java.util.*

class PairNode(
    var left: Node,
    var right: Node
) : Node() {

    companion object {
        fun parse(input: String): PairNode = parse(CharBuffer(input.trim()))

        private fun parse(buffer: CharBuffer): PairNode {

            assert(buffer.remove(1).single() == '[')
            val leftNode = parseNode(buffer)
            assert(buffer.remove(1).single() == ',')
            val rightNode = parseNode(buffer)
            assert(buffer.remove(1).single() == ']')

            val pair = PairNode(leftNode, rightNode)

            return pair
        }

        private fun parseNode(buffer: CharBuffer): Node = when (buffer.peek(1).single()) {
            '[' -> parse(buffer)
            else -> LiteralNode(buffer.remove(1).single().toString().toLong())
        }
    }

    override fun magnitude(): Long = (3 * left.magnitude()) + (2 * right.magnitude())

    fun explodeCandidates(nestLevel: Int): List<PairNode> = if (nestLevel >= 4) {
        listOf(this)
    } else {
        val leftCandidates = (left as? PairNode)?.explodeCandidates(nestLevel + 1) ?: emptyList()
        val rightCandidates = (right as? PairNode)?.explodeCandidates(nestLevel + 1) ?: emptyList()
        leftCandidates + rightCandidates
    }

    fun splitCandidates(): List<LiteralNode> = specificSplitCandidates(left) + specificSplitCandidates(right)

    fun buildParentMap(): Map<Node, PairNode> = mapOf(
        left to this,
        right to this
    ) + ((left as? PairNode)?.buildParentMap() ?: emptyMap()) + ((right as? PairNode)?.buildParentMap() ?: emptyMap())

    fun searchLeft(
        parentMap: Map<Node, PairNode>,
        searchSet: Set<UUID>
    ): LiteralNode? = when {
        left is LiteralNode -> left as LiteralNode
        left.searchId !in searchSet -> (left as PairNode).nextRightLiteral()
        parentMap[this] != null -> parentMap.getValue(this).searchLeft(parentMap, searchSet + this.searchId)
        else -> null
    }

    private fun nextRightLiteral(): LiteralNode = when (right) {
        is LiteralNode -> right as LiteralNode
        else -> (right as PairNode).nextRightLiteral()
    }

    fun searchRight(
        parentMap: Map<Node, PairNode>,
        searchSet: Set<UUID>
    ): LiteralNode? = when {
        right is LiteralNode -> right as LiteralNode
        right.searchId !in searchSet -> (right as PairNode).nextLeftLiteral()
        parentMap[this] != null -> parentMap.getValue(this).searchRight(parentMap, searchSet + this.searchId)
        else -> null
    }

    private fun nextLeftLiteral(): LiteralNode = when (left) {
        is LiteralNode -> left as LiteralNode
        else -> (left as PairNode).nextLeftLiteral()
    }

    private fun specificSplitCandidates(
        node: Node
    ): List<LiteralNode> = if (node is LiteralNode) {
        if (node.value >= 10) {
            listOf(node)
        } else {
            emptyList()
        }
    } else {
        (node as PairNode).splitCandidates()
    }

    override fun toString(): String {
        return "[$left,$right]"
    }

}