package com.closeratio.aoc2021.day16.packet

import com.closeratio.aoc2021.day16.CharBuffer
import com.closeratio.aoc2021.day16.toLong

abstract class Packet(
    val version: Int
) {
    companion object {
        internal fun getVersionAndType(buffer: CharBuffer): Pair<Int, Int> = buffer.remove(6)
            .let {
                val version = it.take(3).toLong().toInt()
                val type = it.takeLast(3).toLong().toInt()
                version to type
            }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Packet) return false

        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        return version
    }

    abstract fun versionSum(): Long

    abstract fun computeValue(): Long

}
