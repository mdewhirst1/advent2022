/*
A = rock
B = paper
C= scissors

X = loose
Y = draw
Z = win

total score is the sum of your scores for each round.
the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
 */

fun main() {
    val input = readResourceFileAsLines("2.txt")
    var totalScore = 0

    for(line in input) {
        if("A" in line) {
            if("X" in line){
                totalScore += (3 + 0)
            }
            if("Y" in line){
                totalScore += (1 + 3)
            }
            if("Z" in line){
                totalScore += (2 + 6)
            }
        }
        else if("B" in line) {
            if("X" in line){
                totalScore += (1 + 0)
            }
            else if("Y" in line){
                totalScore += (2 + 3)
            }
            else if("Z" in line){
                totalScore += (3 + 6)
            }
        }
        else if("C" in line) {
            if("X" in line){
                totalScore += (2 + 0)
            }
            else if("Y" in line){
                totalScore += (3 + 3)
            }
            else if("Z" in line){
                totalScore += (1 + 6)
            }
        }
    }

    println("final score following guide is: $totalScore")
}