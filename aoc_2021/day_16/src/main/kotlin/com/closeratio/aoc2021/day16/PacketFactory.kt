package com.closeratio.aoc2021.day16

import com.closeratio.aoc2021.common.text.CharBuffer
import com.closeratio.aoc2021.day16.packet.*

object PacketFactory {

    fun createPacket(buffer: CharBuffer): Packet = when (getTypeId(buffer)) {
        4 -> LiteralValue.parse(buffer)
        else -> parseOperator(buffer)
    }

    private fun parseOperator(buffer: CharBuffer): Operator {
        val (version, type) = Packet.getVersionAndType(buffer)

        val packets = when (val bit = buffer.remove(1).toLong().toInt()) {
            0 -> parseSubpacketsWithLength(buffer, buffer.remove(15).toLong().toInt())
            1 -> parseSubpacketsWithCount(buffer, buffer.remove(11).toLong().toInt())
            else -> throw IllegalStateException("Unexpected bit value: $bit")
        }

        return when (type) {
            0 -> Sum(version, packets)
            1 -> Product(version, packets)
            2 -> Minimum(version, packets)
            3 -> Maximum(version, packets)
            5 -> GreaterThan(version, packets)
            6 -> LessThan(version, packets)
            7 -> EqualTo(version, packets)
            else -> throw IllegalArgumentException("Unknown type: $type")
        }
    }

    private fun parseSubpacketsWithLength(
        buffer: CharBuffer,
        length: Int
    ): List<Packet> {
        val subpacketBuffer = CharBuffer(buffer.remove(length))

        val packets = mutableListOf<Packet>()
        while (subpacketBuffer.isNotEmpty()) {
            packets.add(PacketFactory.createPacket(subpacketBuffer))
        }

        return packets
    }

    private fun parseSubpacketsWithCount(
        buffer: CharBuffer,
        count: Int
    ): List<Packet> = IntRange(1, count).map { PacketFactory.createPacket(buffer) }

    internal fun getTypeId(buffer: CharBuffer): Int = buffer
        .peek(6)
        .drop(3)
        .joinToString("")
        .toInt(2)

}