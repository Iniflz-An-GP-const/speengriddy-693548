package simostudio96.speengriddy.hubapp.ui.theme.typography

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

data class Fonts(
    val body: FontFamily,
    val display: FontFamily,
    /**
     * Weight of every display/headline/title style. Set it to a weight that
     * [display] really has: a single-weight face (e.g. Anton) must stay Normal,
     * otherwise Android fakes bold on top of it.
     */
    val displayWeight: FontWeight,
    /**
     * Size multiplier for display/headline/title styles. Condensed faces have a
     * small x-height and look a step smaller than Material's defaults; 1f means
     * "use the Material size as is".
     */
    val displayScale: Float = 1f,
)
