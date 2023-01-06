/*
Each tree is represented as a single digit whose value is its height, where 0 is the shortest and 9 is the tallest.

A tree is visible if all of the other trees between it and an edge of the grid are shorter than it.
Only consider trees in the same row or column; that is, only look up, down, left, or right from any given tree.

All of the trees around the edge of the grid are visible - since they are already on the edge, there are no trees to block the view.
In this example, that only leaves the interior nine trees to consider:

The top-left 5 is visible from the left and top. (It isn't visible from the right or bottom since other trees of height 5 are in the way.)
The top-middle 5 is visible from the top and right.
The top-right 1 is not visible from any direction; for it to be visible, there would need to only be trees of height 0 between it and an edge.
The left-middle 5 is visible, but only from the right.
The center 3 is not visible from any direction; for it to be visible, there would need to be only trees of at most height 2 between it and an edge.
The right-middle 3 is visible from the right.
In the bottom row, the middle 5 is visible, but the 3 and 4 are not.
With 16 trees visible on the edge and another 5 visible in the interior, a total of 21 trees are visible in this arrangement.

Consider your map; how many trees are visible from outside the grid? 1538

--- Part Two ---

To measure the viewing distance from a given tree, look up, down, left, and right from that tree;
stop if you reach an edge or at the first tree that is the same height or taller than the tree under consideration.
(If a tree is right on the edge, at least one of its viewing distances will be zero.)

A tree's scenic score is found by multiplying together its viewing distance in each of the four directions.
consider the middle 5 in the second row for this tree, this is 4 (found by multiplying 1 * 1 * 2 * 2).

What is the highest scenic score possible for any tree? 496125
 */

private val gridOfTreeHeights: List<List<Int>> = readResourceFileAsLines("8.txt").map { line -> line.map { it.digitToInt() } }

fun main() {
    println("part 1: ${part1()}")
    println("part 2: ${part2()}")
}

private fun part1(): Int {
    var count = 0
    for (x in gridOfTreeHeights.indices) {
        for (y in gridOfTreeHeights[x].indices) {
            if (isVisable(x, y)) count ++
        }
    }
    return count
}

private fun part2(): Int {
    var highest = 0
    for (x in gridOfTreeHeights.indices) {
        for (y in gridOfTreeHeights[x].indices) {
            val scenicScore = scenicScore(x, y)
            if (scenicScore > highest) highest = scenicScore
        }
    }
    return highest
}

fun isVisable(x: Int, y: Int): Boolean =
    (y - 1 downTo 0).all { yPos -> gridOfTreeHeights[x][yPos] < gridOfTreeHeights[x][y] } ||
            (y + 1 until  gridOfTreeHeights[x].size).all { yPos -> gridOfTreeHeights[x][yPos] < gridOfTreeHeights[x][y] } ||
            (x - 1 downTo 0).all { xPos -> gridOfTreeHeights[xPos][y] < gridOfTreeHeights[x][y] } ||
            (x + 1 until  gridOfTreeHeights.size).all { xPos -> gridOfTreeHeights[xPos][y] < gridOfTreeHeights[x][y] }

fun scenicScore(x: Int, y: Int) =
    ((y - 1 downTo 1).takeWhile { yPos -> gridOfTreeHeights[x][y] > gridOfTreeHeights[x][yPos] }.count() + 1) *
            ((y + 1 until gridOfTreeHeights[x].size - 1).takeWhile { yPos -> gridOfTreeHeights[x][y] > gridOfTreeHeights[x][yPos] }.count() + 1) *
            ((x + 1 until gridOfTreeHeights.size - 1).takeWhile { xPos -> gridOfTreeHeights[x][y] > gridOfTreeHeights[xPos][y] }.count() + 1) *
            ((x - 1 downTo 1).takeWhile { xPos -> gridOfTreeHeights[x][y] > gridOfTreeHeights[xPos][y] }.count() + 1)