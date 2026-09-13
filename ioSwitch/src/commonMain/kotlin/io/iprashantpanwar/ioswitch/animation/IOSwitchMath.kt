package io.iprashantpanwar.ioswitch.animation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import io.iprashantpanwar.ioswitch.model.IOSwitchType
import io.iprashantpanwar.ioswitch.model.aspectRatio

/**
 * Performs linear interpolation between two floating-point values.
 *
 * A fraction of `0f` returns [start], while a fraction of `1f`
 * returns [stop].
 *
 * @param start Starting value.
 * @param stop Ending value.
 * @param fraction Interpolation progress.
 * @return Interpolated value between [start] and [stop].
 */
internal fun lerp(
    start: Float,
    stop: Float,
    fraction: Float,
): Float {
    return start + (stop - start) * fraction
}
