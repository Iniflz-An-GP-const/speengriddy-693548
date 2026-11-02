package simostudio96.speengriddy.hubapp.data.repository

import simostudio96.speengriddy.hubapp.R
import simostudio96.speengriddy.hubapp.data.model.Puzzle

class PuzzleRepository {
    private val puzzles: List<Puzzle> = listOf(
        
    )

    fun getPuzzles(): List<Puzzle> = puzzles

    fun findById(id: Int): Puzzle? = puzzles.firstOrNull { it.id == id }
}