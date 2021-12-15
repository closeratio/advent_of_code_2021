package com.closeratio.aoc2021.day14

data class InsertionRule(
    val pattern: Pair<Char, Char>,
    val insertion: Pair<Pair<Char, Char>, Pair<Char, Char>>
)
