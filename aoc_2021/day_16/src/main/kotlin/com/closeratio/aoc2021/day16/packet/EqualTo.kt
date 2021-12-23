package com.closeratio.aoc2021.day16.packet

class EqualTo(
    version: Int,
    packets: List<Packet>
): Operator(version, packets) {

    override fun computeValue(): Long = if (packets[0].computeValue() == packets[1].computeValue()) 1 else 0

}