package com.closeratio.aoc2021.day16.packet

class Minimum(
    version: Int,
    packets: List<Packet>
) : Operator(version, packets) {

    override fun computeValue(): Long = packets.minOf(Packet::computeValue)

}