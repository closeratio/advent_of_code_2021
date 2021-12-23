package com.closeratio.aoc2021.day16

import com.closeratio.aoc2021.day16.packet.Packet

class PacketDecoder {

    fun parseTransmission(transmission: String): Packet {
        val buffer: CharBuffer = transmission
            .trim()
            .map(::parseHex)
            .joinToString("")
            .toList()
            .let(::CharBuffer)

        return PacketFactory.createPacket(buffer)
    }

    fun parseHex(hexChar: Char): String = hexChar
        .toString()
        .toInt(16)
        .toString(2)
        .padStart(4, '0')

}