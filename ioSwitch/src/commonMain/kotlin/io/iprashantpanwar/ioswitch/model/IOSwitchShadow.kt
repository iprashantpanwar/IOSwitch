package io.iprashantpanwar.ioswitch.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset

@Immutable
data class IOSwitchShadow(
    val color: Color?,
    val alpha: Float,
    val radius: Dp,
    val spread: Dp,
    val offset: DpOffset,
)
