package simostudio96.speengriddy.hubapp.ui.composable.app_root.background.points

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.sin

private const val GROUP_SIZE = 3
private const val GROUP_RADIUS = 0.09f

fun backgroundPoints(count: Int): List<Offset> {
    val groups = ceil(count / GROUP_SIZE.toFloat()).toInt().coerceAtLeast(1)
    val centers = List(groups) { index ->
        Offset(
            x = (index + 0.5f) / groups,
            y = ((index * 2) % groups + 0.5f + 0.37f * (index % 2)) / groups % 1f
        )
    }

    return centers.flatMap { center ->
        List(GROUP_SIZE) { index ->
            val angle = (2.0 * PI * index / GROUP_SIZE).toFloat() + center.x * 6f
            Offset(
                x = (center.x + cos(angle) * GROUP_RADIUS + 1f) % 1f,
                y = (center.y + sin(angle) * GROUP_RADIUS + 1f) % 1f,
            )
        }
    }
}
