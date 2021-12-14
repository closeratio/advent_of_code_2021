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
                                pair[0] to pair[1], insertion
                            )
                        }
                )
            }

    }

    fun quantityDifference(stepCount: Int): Int = IntRange(1, stepCount)
        .fold(template.template) { acc, _ -> applyRules(acc) }
        .groupBy({ it }) { 1 }
        .mapValues { (_, v) -> v.size }
        .let { map ->
            map.maxOf { it.value } - map.minOf { it.value }
        }

    private fun applyRules(input: String): String = input
        .zipWithNext()
        .joinToString("") { pair ->
            pair.first + ruleMap.getValue(pair)
        } + input.last()

}
