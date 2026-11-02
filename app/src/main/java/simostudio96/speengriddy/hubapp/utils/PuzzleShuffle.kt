package simostudio96.speengriddy.hubapp.utils

/**
 * Returns a shuffled list of the indices 0..n-1 that is guaranteed to differ
 * from the original order (so the game never starts already "solved").
 */
fun shuffledOrder(size: Int): List<Int> {
    val solved = (0 until size).toList()
    var candidate: List<Int>
    do {
        candidate = solved.shuffled()
    } while (candidate == solved)
    return candidate
}
