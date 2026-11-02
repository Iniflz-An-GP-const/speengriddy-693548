package simostudio96.speengriddy.hubapp.ui.composable.app_root.background

/** Two values read while drawing, so animating them never recomposes: [shift] is
 * the progress (0..1, wrapping) of a seamless slide over one tile, [alpha] multiplies
 * the pattern's opacity. */
class BackgroundMotion(
    val shift: () -> Float,
    val alpha: () -> Float,
)
