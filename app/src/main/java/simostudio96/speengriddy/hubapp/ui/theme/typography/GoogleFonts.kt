package simostudio96.speengriddy.hubapp.ui.theme.typography

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import simostudio96.speengriddy.hubapp.R

private val GoogleFontsProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private val DefaultWeights = listOf(
    FontWeight.Normal,
    FontWeight.Medium,
    FontWeight.SemiBold,
    FontWeight.Bold,
)

/**
 * [weights] must be the weights the family really has on Google Fonts:
 * asking for a missing one makes the provider fail and the text silently
 * falls back to the system font.
 */
fun googleFontFamily(
    name: String,
    weights: List<FontWeight> = DefaultWeights,
): FontFamily {
    val googleFont = GoogleFont(name)

    return FontFamily(
        weights.map { weight ->
            Font(
                googleFont = googleFont,
                fontProvider = GoogleFontsProvider,
                weight = weight
            )
        }
    )
}
