import java.io.File

fun main(args: Array<String>) {
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")

    val fileName = "/Users/Sara.Kato/IdeaProjects/AOC/src/main/kotlin/06-input.txt" // Replace with the actual file name or path
    var runningSum = 0
    var times = mutableListOf<Long>()
    var distances = mutableListOf<Long>()
    var timeP2 = 0L
    var distanceP2 = 0L
    try {
        val file = File(fileName)

        // Check if the file exists
        if (file.exists()) {
            // Read each line from the file and print it
            file.forEachLine {
                // first line is time
                // second is distance
                if (it.contains("Time:")) {
                    // parse times
                    times = parseData(it, "Time")
                    timeP2 = parseDataP2(it, "Time")
                } else if (it.contains("Distance:")) {
                    distances = parseData(it, "Distance")
                    distanceP2 = parseDataP2(it, "Distance")
                }
            }
            println("Times=$times")
            println("Distances=$distances")
        } else {
            println("File not found: $fileName")
        }
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    }

    var i = 0;
    var currentSpeed = 0
    var winCount = 0L
    var win = 1L
    /*for (time in times) {
        // this is where we calculate it.
        // Do it, as long as currentSpeed <= time
        val winningDistance = distances[i]
        while (currentSpeed <= time) {
            // calculate the distance we can go.
            val remainingTime = time - currentSpeed
            val distanceTravelled = currentSpeed * remainingTime
            if (distanceTravelled > winningDistance) {
                winCount ++
                //println("Round $i, $currentSpeed is winning!")
            }
            currentSpeed ++
        }
        currentSpeed = 0
        //println("Win count=$winCount")
        i ++
        win *= winCount
        winCount = 0
    }*/

        // this is where we calculate it.
        // Do it, as long as currentSpeed <= time
    val winningDistance = distanceP2
    println("Computing time=$timeP2, distance=$distanceP2")
    while (currentSpeed <= timeP2) {
        // calculate the distance we can go.
        val remainingTime = timeP2 - currentSpeed
        val distanceTravelled = currentSpeed * remainingTime
        if (distanceTravelled > winningDistance) {
            winCount ++
            //println("Round $i, $currentSpeed is winning!")
        }
        currentSpeed ++
    }
    currentSpeed = 0
    //println("Win count=$winCount")
    println("win value=$winCount")
}

fun parseData(times: String, prefix: String): MutableList<Long> {
    val timesList = mutableListOf<Long>()
    val times = times.replace("$prefix: ", "")
    println("times=$times")
    val regex = "\\s+".toRegex()
    val time = times.split(regex)
    println("time=$time")
    for (it in time) {
        println("it='$it'")// ifit != '', add it
    }
    return timesList
}

fun parseDataP2(times: String, prefix: String): Long {
    val times = times.replace("$prefix: ", "")
    val regex = "\\s+".toRegex()
    val time = times.replace(regex, "")
    return time.toLong()
}
