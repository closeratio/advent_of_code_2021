package com.closeratio.aoc2021.day12

import com.closeratio.aoc2021.day12.CaveType.BIG
import com.closeratio.aoc2021.day12.CaveType.SMALL

class Cave(
    val name: String
) {

    val type: CaveType = when (name) {
        name.uppercase() -> BIG
        name.lowercase() -> SMALL
        else -> throw IllegalArgumentException("Unable to determine cave type: $name")
    }

    val linkedCaves = hashSetOf<Cave>()

    fun link(other: Cave) {
        linkedCaves.add(other)
        if (!other.linkedCaves.contains(this)) {
            other.link(this)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cave) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return "Cave(name='$name')"
    }

    fun paths(currentPath: List<Cave>): List<List<Cave>> {
        if (name == "end") {
            return listOf(currentPath)
        }

        return linkedCaves
            .filter { it !in currentPath || it.type == BIG }
            .flatMap { it.paths(currentPath + it) }
    }

    fun pathsComplex(currentPath: List<Cave>): List<List<Cave>> {
        if (name == "end") {
            return listOf(currentPath)
        }

        return linkedCaves
            .filter { candidate ->
                when {
                    candidate !in currentPath -> true
                    candidate.type == BIG -> true
                    candidate.name == "start" -> false
                    currentPath
                        .filter { it.type == SMALL }
                        .groupBy(Cave::name)
                        .values
                        .map(List<Cave>::size)
                        .all { it == 1 } -> true
                    else -> false
                }
            }
            .flatMap { it.pathsComplex(currentPath + it) }
    }

}