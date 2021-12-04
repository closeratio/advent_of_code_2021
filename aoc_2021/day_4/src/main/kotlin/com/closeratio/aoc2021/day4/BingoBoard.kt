package com.closeratio.aoc2021.day4

class BingoBoard(
    inputData: String
) {

    private val unoptimisedRows = inputData
        .trim()
        .split("\n")
        .map(String::trim)
        .map { row -> row.split("""\s+""".toRegex()) }
        .map { row -> row.map { cell -> cell.toInt() } }

    val rows: List<Set<Int>> = unoptimisedRows.map(List<Int>::toSet)
    val columns: List<Set<Int>> = IntRange(0, rows.size - 1)
        .map { columnIndex ->
            IntRange(0, rows.size - 1).map { rowIndex ->
                unoptimisedRows[rowIndex][columnIndex]
            }
        }
        .map(List<Int>::toSet)

    fun playNumbers(
        numbers: Set<Int>
    ): Int? {
        val winningRow = rows.firstOrNull { (it - numbers).isEmpty() }
        val winningColumn = columns.firstOrNull { (it - numbers).isEmpty() }

        if (winningRow == null && winningColumn == null) {
            return null
        }

        val unmarked = unoptimisedRows
            .flatten()
            .toSet() - numbers

        return unmarked.sum() * numbers.last()
    }

}