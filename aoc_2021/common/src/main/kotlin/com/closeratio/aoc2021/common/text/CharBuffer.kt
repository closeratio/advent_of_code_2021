package com.closeratio.aoc2021.common.text

import java.util.*

class CharBuffer(
    input: List<Char>
) {
    constructor(input: String): this(input.toList())

    private val buffer: LinkedList<Char> = LinkedList(input)

    /**
     * Doesn't mutate the buffer
     */
    fun peek(amount: Int): List<Char> = buffer.take(amount)

    /**
     * Consumes from the buffer
     */
    fun remove(amount: Int): List<Char> = IntRange(1, amount)
        .fold(emptyList()) { acc, _ ->
            acc + buffer.remove()
        }

    /**
     * Consumes from the buffer
     */
    fun remove(): Char = buffer.remove()

    fun isEmpty(): Boolean = buffer.isEmpty()
    fun isNotEmpty(): Boolean = buffer.isNotEmpty()

}