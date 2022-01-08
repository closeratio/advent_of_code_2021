package com.closeratio.aoc2021.day24

import kotlin.Long.Companion.MAX_VALUE
import kotlin.Long.Companion.MIN_VALUE

class AluSolver(
    val parameterList: List<Parameters>
) {

    companion object {
        fun parse(input: String): AluSolver = input
            .trim()
            .split("\n")
            .chunked(18)
            .map { chunk ->
                Parameters(
                    chunk[4].substringAfterLast(" ").toLong(),
                    chunk[5].substringAfterLast(" ").toLong(),
                    chunk[15].substringAfterLast(" ").toLong()
                )
            }
            .let(::AluSolver)
    }

    data class Parameters(
        val a: Long,
        val b: Long,
        val c: Long
    )

    data class MinMax(
        val min: Long,
        val max: Long
    )

    private fun checksum(
        parameters: Parameters,
        z: Long,
        w: Long
    ): Long {
        return if ((z % 26 + parameters.b) != w) {
            ((z / parameters.a) * 26) + w + parameters.c
        } else {
            z / parameters.a
        }
    }

    fun solve(): MinMax {
        var zValues = mutableMapOf(0L to MinMax(0, 0))

        parameterList.forEach { parameters ->
            val zValuesThisRound = mutableMapOf<Long, MinMax>()
            zValues.forEach { (z, minMax) ->
                (1L..9L)
                    .map { digit -> digit to checksum(parameters, z, digit) }
                    .filter { (_, newValueForZ) -> parameters.a == 1L || (parameters.a == 26L && newValueForZ < z) }
                    .forEach { (digit, newValueForZ) ->
                        val min = minOf(zValuesThisRound[newValueForZ]?.min ?: MAX_VALUE, minMax.min * 10L + digit)
                        val max = maxOf(zValuesThisRound[newValueForZ]?.max ?: MIN_VALUE, minMax.max * 10L + digit)
                        zValuesThisRound[newValueForZ] = MinMax(min, max)
                    }
            }
            zValues = zValuesThisRound
        }

        return zValues.getValue(0)
    }
}