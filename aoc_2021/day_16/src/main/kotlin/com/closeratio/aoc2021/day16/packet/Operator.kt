package com.closeratio.aoc2021.day16.packet

abstract class Operator(
    version: Int,
    val packets: List<Packet>
) : Packet(version) {

    override fun versionSum(): Long = version.toLong() + packets.sumOf(Packet::versionSum)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as Operator

        if (packets != other.packets) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + packets.hashCode()
        return result
    }

    override fun toString(): String {
        return "${javaClass.simpleName}(version=$version, packets=$packets)"
    }

}

