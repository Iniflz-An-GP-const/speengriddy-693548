package simostudio96.speengriddy.hubapp.data.model

import androidx.annotation.DrawableRes

/**
 * [id] is the identifier: saved records point at a puzzle by it, and it never
 * changes. [texts] maps a two-letter language code ("en", "de", ...) to the
 * title shown to the player; the "en" entry is mandatory, it is the fallback.
 */
data class Puzzle(
    val id: Int,
    val texts: Map<String, String>,
    @param:DrawableRes val resId: Int,
) {
    val title: String get() = texts.forCurrentLocale()
}
