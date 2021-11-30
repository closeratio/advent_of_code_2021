rootProject.name = "advent_of_code_2021"

include(":aoc_2021:common")

(1..25).forEach {
    include(":aoc_2021:day_$it")
}
