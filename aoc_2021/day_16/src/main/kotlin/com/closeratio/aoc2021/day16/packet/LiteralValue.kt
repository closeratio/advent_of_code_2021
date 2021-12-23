package com.closeratio.aoc2021.day16.packet

import com.closeratio.aoc2021.day16.CharBuffer
import com.closeratio.aoc2021.day16.toLong

class LiteralValue(
    version: Int,
    val value: Long
): Packet(version) {

    companion object {
        fun parse(buffer: CharBuffer): LiteralValue {
            val (version, type) = getVersionAndType(buffer)
            assert(type == 4) { "Type for literal should be 4" }

            val groups = mutableListOf<List<Char>>()
            while (buffer.peek(1).toLong() == 1L) {
                groups.add(buffer.remove(5).drop(1))
            }
            groups.add(buffer.remove(5).drop(1))

            val value = groups.flatten().toLong()

            return LiteralValue(version, value)
        }
    }

    override fun versionSum(): Long = version.toLong()

    override fun computeValue(): Long = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LiteralValue) return false
        if (!super.equals(other)) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    override fun toString(): String {
        return "LiteralValue(version=$version, value=$value)"
    }

}
