package simostudio96.speengriddy.hubapp.ui.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle

fun appTypography(fonts: Fonts): Typography {
    val default = Typography()

    fun TextStyle.display() = copy(
        fontFamily = fonts.display,
        fontWeight = fonts.displayWeight,
        fontSize = fontSize * fonts.displayScale,
        lineHeight = lineHeight * fonts.displayScale,
    )

    fun TextStyle.body() = copy(fontFamily = fonts.body)

    return Typography(
        displayLarge = default.displayLarge.display(),
        displayMedium = default.displayMedium.display(),
        displaySmall = default.displaySmall.display(),

        headlineLarge = default.headlineLarge.display(),
        headlineMedium = default.headlineMedium.display(),
        headlineSmall = default.headlineSmall.display(),

        titleLarge = default.titleLarge.display(),
        titleMedium = default.titleMedium.display(),
        titleSmall = default.titleSmall.display(),

        bodyLarge = default.bodyLarge.body(),
        bodyMedium = default.bodyMedium.body(),
        bodySmall = default.bodySmall.body(),

        labelLarge = default.labelLarge.body(),
        labelMedium = default.labelMedium.body(),
        labelSmall = default.labelSmall.body(),
    )
}
