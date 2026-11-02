package simostudio96.speengriddy.hubapp.ui.composable.shared.pulse

data class PulseTiming(
    val durationMs: Int,
    val pauseMs: Int,
    /** Strength of the pulse: 1 is the standard amplitude, 0 turns the effect off. */
    val intensity: Float,
) {
    val isOff: Boolean get() = intensity <= 0f

    val cycleMs: Int get() = durationMs + pauseMs
}
