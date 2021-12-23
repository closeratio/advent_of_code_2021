package com.closeratio.aoc2021.day16.packet

class Product(
    version: Int,
    packets: List<Packet>
): Operator(version, packets) {

    override fun computeValue(): Long = packets
        .map(Packet::computeValue)
        .reduce { acc, curr -> acc * curr }

}