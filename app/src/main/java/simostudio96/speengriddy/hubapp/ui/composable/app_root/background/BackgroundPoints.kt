package simostudio96.speengriddy.hubapp.ui.composable.app_root.background

import androidx.compose.ui.geometry.Offset

/** Positions of the elements of a scattered pattern inside one repeating tile,
 *  as fractions (0..1) of the tile. A variant may return a few more or fewer points than asked. */
typealias BackgroundPoints = (count: Int) -> List<Offset>
