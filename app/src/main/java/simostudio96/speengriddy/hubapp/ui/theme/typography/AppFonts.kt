package simostudio96.speengriddy.hubapp.ui.theme.typography

import androidx.compose.ui.text.font.FontWeight
import simostudio96.speengriddy.hubapp.ui.theme.typography.Fonts
import simostudio96.speengriddy.hubapp.ui.theme.typography.googleFontFamily

val AppFonts = Fonts(
    body = googleFontFamily("Inter"),
    display = googleFontFamily("Anton", weights = listOf(FontWeight.Normal)),
    displayWeight = FontWeight.Normal,
    displayScale = 1.1f,
)
