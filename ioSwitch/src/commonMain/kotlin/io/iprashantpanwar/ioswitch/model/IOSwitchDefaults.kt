package io.iprashantpanwar.ioswitch.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

object IOSwitchDefaults {

    @Composable
    fun colors(
        checkedTrackColor: Color = Color(0xFF48EA8B),
        uncheckedTrackColor: Color = Color(0xFFFF4651),
        thumbColor: Color = Color.White,
    ): IOSwitchColors {
        return IOSwitchColors(
            checkedTrackColor = checkedTrackColor,
            uncheckedTrackColor = uncheckedTrackColor,
            thumbColor = thumbColor
        )
    }

    @Composable
    fun shadow(
        color: Color? = null,
        alpha: Float = 0.32f,
        radius: Dp = 8.dp,
        spread: Dp = 4.dp,
        offset: DpOffset = DpOffset(
            x = 0.dp,
            y = 4.dp
        ),
    ): IOSwitchShadow {
        return IOSwitchShadow(
            color = color,
            alpha = alpha,
            radius = radius,
            spread = spread,
            offset = offset,
        )
    }
}