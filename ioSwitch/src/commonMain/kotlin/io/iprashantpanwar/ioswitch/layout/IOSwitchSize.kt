package io.iprashantpanwar.ioswitch.layout

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import io.iprashantpanwar.ioswitch.model.IOSwitchType
import io.iprashantpanwar.ioswitch.model.aspectRatio

/**
 * Calculates the final dimensions of an IOSwitch while preserving
 * the aspect ratio of its visual type.
 *
 * When the requested dimensions have different proportions, the
 * switch fits within those dimensions rather than stretching.
 *
 * For example, a pill requested as `64.dp × 36.dp` becomes
 * `36.dp × 36.dp`.
 *
 * A capsule requested as `100.dp × 90.dp` becomes approximately
 * `100.dp × 56.25.dp`.
 *
 * @param type Visual type whose aspect ratio should be preserved.
 * @param width Requested width.
 * @param height Requested height.
 * @return Resolved dimensions preserving the type's aspect ratio.
 */
internal fun resolveIOSwitchSize(
    type: IOSwitchType,
    width: Dp,
    height: Dp,
): DpSize {
    val aspectRatio = type.aspectRatio

    val widthBasedHeight = width / aspectRatio

    return if (widthBasedHeight <= height) {
        DpSize(
            width = width,
            height = widthBasedHeight
        )
    } else {
        DpSize(
            width = height * aspectRatio,
            height = height
        )
    }
}
