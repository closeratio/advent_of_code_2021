package com.closeratio.aoc2021.day12

data class CaveSystem(
    val caves: Map<String, Cave>
) {

    companion object {
        fun parse(input: String): CaveSystem = input
                .trim()
                .split("\n")
                .map(String::trim)
                .map { it.split("-") }
                .fold(
                    HashMap<String, Cave>()
                ) { map, (left, right) ->
                    val leftCave = map.getOrPut(left) { Cave(left) }
                    val rightCave = map.getOrPut(right) { Cave(right) }
                    leftCave.link(rightCave)
                    map
                }
                .let(::CaveSystem)
    }

    fun pathCount(): Int {
        val start = caves.getValue("start")
        val paths = start.paths(listOf(start))

        return paths.size
    }

    fun pathCountComplex(): Int {
        val start = caves.getValue("start")
        val paths = start.pathsComplex(listOf(start))

        return paths.size
    }

}

