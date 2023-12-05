import java.io.File
import java.lang.Integer.min

fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")

    val fileName = "/Users/Sara.Kato/IdeaProjects/AOC/src/main/kotlin/01-input-02.txt" // Replace with the actual file name or path
    var runningSum = 0
    try {
        val file = File(fileName)

        // Check if the file exists
        if (file.exists()) {
            // Read each line from the file and print it
            file.forEachLine {
                val firstCalibration = getFirstCalibrationValue(it)
                val secondCalibration = getSecondCalibrationValue(it)
                val total =  firstCalibration + secondCalibration
                println("$it=${total}, first=$firstCalibration")
                runningSum += total
            }
        } else {
            println("File not found: $fileName")
        }
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    }
    println("total=$runningSum")
}

fun findStringIndex(input: String): Int? {
    val indexList = listOf(input.indexOf("one"), input.indexOf("two"), input.indexOf("three"),
        input.indexOf("four"), input.indexOf("five"), input.indexOf("six"),
        input.indexOf("seven"), input.indexOf("eight"), input.indexOf("nine"))
    val filtered = indexList.filter { it != -1 }
    return filtered.minOrNull()
}

fun findLastStringIndex(input: String): Int? {
    val indexList = listOf(input.lastIndexOf("one"), input.lastIndexOf("two"), input.lastIndexOf("three"),
        input.lastIndexOf("four"), input.lastIndexOf("five"), input.lastIndexOf("six"),
        input.lastIndexOf("seven"), input.lastIndexOf("eight"), input.lastIndexOf("nine"))

    val filtered = indexList.filter { it != -1 }
    return filtered.maxOrNull()
}

fun firstStringValue(input: String, minIndex: Int): Int {
    if (minIndex == input.indexOf("one")) {
        return 1
    }

    if (minIndex == input.indexOf("two")) {
        return 2
    }

    if (minIndex == input.indexOf("three")) {
        return 3
    }

    if (minIndex == input.indexOf("four")) {
        return 4
    }

    if (minIndex == input.indexOf("five")) {
        return 5
    }

    if (minIndex == input.indexOf("six")) {
        return 6
    }

    if (minIndex == input.indexOf("seven")) {
        return 7
    }

    if (minIndex == input.indexOf("eight")) {
        return 8
    }

    if (minIndex == input.indexOf("nine")) {
        return 9
    }
    return -1
}

fun lastStringValue(input: String, minIndex: Int): Int {
    if (minIndex == input.lastIndexOf("one")) {
        return 1
    }

    if (minIndex == input.lastIndexOf("two")) {
        return 2
    }

    if (minIndex == input.lastIndexOf("three")) {
        return 3
    }

    if (minIndex == input.lastIndexOf("four")) {
        return 4
    }

    if (minIndex == input.lastIndexOf("five")) {
        return 5
    }

    if (minIndex == input.lastIndexOf("six")) {
        return 6
    }

    if (minIndex == input.lastIndexOf("seven")) {
        return 7
    }

    if (minIndex == input.lastIndexOf("eight")) {
        return 8
    }

    if (minIndex == input.lastIndexOf("nine")) {
        return 9
    }
    return -1
}

fun getFirstCalibrationValue(input: String): Int {
    // Find the string index.
    val firstStringIndex = findStringIndex(input)

    if (firstStringIndex == 0) {

        return firstStringValue(input, firstStringIndex) * 10
    }
    var currIndex = 0
    for (it in input) {
        val firstDigit = parseStringToInt(it)

        if (firstDigit != -1) { // gives value. not index

            if (firstStringIndex != null &&  firstStringIndex < currIndex) {
                // take first string index.
                // how to get the corresponding value form here?
                //
                // println("    $input firstString=${firstStringValue(input, firstStringIndex)}")
                return firstStringValue(input, firstStringIndex) * 10
            }
            return firstDigit * 10
        }
        currIndex ++
    }
    return -1
}
fun getSecondCalibrationValue(input: String): Int {
    val lastStringIndex = findLastStringIndex(input)

    // go backwards
    for (index in input.length - 1 downTo 0) {

        val secondDigit = parseStringToInt(input[index])
        if (secondDigit != -1) {
            //println("   input=$input lastStringIndex=$lastStringIndex, index=$index")
            if (lastStringIndex != null && lastStringIndex > index) {
                return lastStringValue(input, lastStringIndex)
            }
            return secondDigit
        }
    }
    if (lastStringIndex != null) {
        return lastStringValue(input, lastStringIndex)
    }
    return -1
}

fun parseStringToInt(input: Char): Int {
    if (input == '1') {
        return 1
    }
    if (input == '2') {
        return 2
    }
    if (input == '3') {
        return 3
    }
    if (input == '4') {
        return 4
    }
    if (input == '5') {
        return 5
    }
    if (input == '6') {
        return 6
    }
    if (input == '7') {
        return 7
    }
    if (input == '8') {
        return 8
    }
    if (input == '9') {
        return 9
    }
    return -1
}