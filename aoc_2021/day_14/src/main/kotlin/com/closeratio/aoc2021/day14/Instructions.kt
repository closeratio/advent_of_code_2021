package com.closeratio.aoc2021.day14

data class Instructions(
    val template: PolymerTemplate,
    val insertionRules: List<InsertionRule>
) {

    private val ruleMap = insertionRules.associateBy({ it.pattern }) { it.insertion }

    companion object {
        fun parse(input: String): Instructions = input
            .trim()
            .split("\n\n")
            .let { (template, rules) ->
                Instructions(
                    PolymerTemplate(template),
                    rules.split("\n")
                        .map(String::trim)
                        .map { it.split(" -> ") }
                        .map { (pair, insertion) ->
                            InsertionRule(
                                pair[0] to pair[1],
                                (pair[0] to insertion[0]) to (insertion[0] to pair[1])
                            )
                        }
                )
            }

    }

    fun quantityDifference(stepCount: Int): Long = IntRange(1, stepCount)
        .fold(
            template.template
                .zipWithNext()
                .groupBy { it }
                .mapValues { (_, v) -> v.size.toLong() }
        ) { acc, _ -> applyRules(acc) }
        .map { (k, v) ->
            k.first to v
        }
        .let {
            it + (template.template.last() to 1L)
        }
        .groupBy({ it.first }) { it.second }
        .mapValues { (_, v) -> v.sum() }
        .let { map ->
            map.maxOf { it.value } - map.minOf { it.value }
        }

    private fun applyRules(input: Map<Pair<Char, Char>, Long>): Map<Pair<Char, Char>, Long> {
        val result = input
            .flatMap { (k, v) ->
                listOf(
                    ruleMap.getValue(k).first to v,
                    ruleMap.getValue(k).second to v
                )
            }
            .groupBy({ it.first }) { it.second }
            .mapValues { (_, v) -> v.sum() }

        return result
    }

}
