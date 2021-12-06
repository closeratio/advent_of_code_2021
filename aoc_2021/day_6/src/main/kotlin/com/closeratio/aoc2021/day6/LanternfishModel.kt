package com.closeratio.aoc2021.day6

class LanternfishModel(
    input: String
) {

    val initialState: Map<TimerValue, FishCount> = input.trim()
        .split(",")
        .map(String::trim)
        .map(String::toInt)
        .groupBy({ TimerValue(it) }) { it }
        .mapValues { (_, v) -> FishCount(v.size.toLong()) }

    fun iterate(dayCount: Int): Long = IntRange(1, dayCount)
            .fold(initialState) { map, _ ->
                map
                    .entries
                    .flatMap { (timer, count) ->
                        if (timer.value == 0) {
                            listOf(
                                TimerValue(6) to count,
                                TimerValue(8) to count
                            )
                        } else {
                            listOf(timer.decrement() to count)
                        }
                    }
                    .groupBy({ it.first }) { it.second }
                    .mapValues { (_, v) -> v.sum() }
            }
            .values
            .toList()
            .sum()
            .count

    private fun List<FishCount>.sum(): FishCount = fold(FishCount(0)) {
            acc, curr -> FishCount(acc.count + curr.count)
    }
}
