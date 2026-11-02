package simostudio96.speengriddy.hubapp.ui.theme.dimens

import androidx.compose.ui.unit.Dp

data class Dimens(
    val screenPadding: Dp,
    val sectionGap: Dp,
    val contentSpacing: Dp,
    val cardPadding: Dp,
    val itemSpacing: Dp,
    val tightSpacing: Dp,
    val cardCornerRadius: Dp,
    val cornerRadius: Dp,
    /**
     * Corner radius of controls — chips and filter pills.
     *
     * A value greater than half the element's height is clipped to that height,
     * resulting in fully rounded ends. This allows the value to express a
     * "pill" shape without requiring a separate flag.
     */
    val controlCornerRadius: Dp,
    /** Border width of cards and slides. Zero means no border. */
    val cardBorderWidth: Dp,
    /** Border width of images inside cards. Zero means no border. */
    val imageBorderWidth: Dp,
    val controlBorderWidth: Dp,
    val pieceGap: Dp,
    val pieceBorderWidth: Dp,
    val pieceSelectedBorderWidth: Dp,
)
