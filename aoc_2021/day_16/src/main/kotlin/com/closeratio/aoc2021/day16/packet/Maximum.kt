package com.closeratio.aoc2021.day16.packet

class Maximum(
    version: Int,
    packets: List<Packet>
): Operator(version, packets) {

    override fun computeValue(): Long = packets.maxOf(Packet::computeValue)

}