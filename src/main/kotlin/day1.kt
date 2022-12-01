private val calorieTotals = mutableListOf<Int>()

fun main() {
    compileCalorieList()

    println("highest calorie total: " + calorieTotals.getSumOfTopX(1))
    println("sum of top 3 calorie totals: " + calorieTotals.getSumOfTopX(3))
}

fun compileCalorieList() {
    val input = readResourceFileAsLines("1.txt")
    var currentTotal = 0

    for(line in input) {
        val calories = line.toIntOrNull()

        if (calories != null) {
            currentTotal += calories
        } else {
            calorieTotals.add(currentTotal)
            currentTotal = 0
        }
    }

    calorieTotals.sortDescending()
}

fun List<Int>.getSumOfTopX(X: Int): Int =
    take(X).sum()