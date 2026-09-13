package io.iprashantpanwar.ioswitch.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import io.iprashantpanwar.ioswitch.model.IOSwitchType

/**
 * Contains the animated values required to render an IOSwitch.
 *
 * The morph and translation animations are kept as separate values
 * because the capsule switch intentionally uses different animation
 * durations for them.
 *
 * @property iconProgress Progress of the thumb morph animation.
 * `0f` represents the checked shape and `1f` represents the
 * unchecked shape.
 *
 * @property translationProgress Progress of the capsule thumb's
 * horizontal translation.
 *
 * @property clickOffset Temporary contraction applied when the
 * switch is tapped.
 */
internal data class IOSwitchAnimationState(
    val iconProgress: Float,
    val translationProgress: Float,
    val clickOffset: Float,
)

/**
 * Creates the animated state for an IOSwitch.
 *
 * Capsule and pill variants share the thumb morph animation.
 * Only the capsule variant uses horizontal translation.
 *
 * @param checked Current checked state.
 * @param type Current switch shape.
 * @param clickOffset Current tap-compression amount.
 */
@Composable
internal fun rememberIOSwitchAnimationState(
    checked: Boolean,
    type: IOSwitchType,
    clickOffset: Float,
): IOSwitchAnimationState {

    val iconProgress by animateFloatAsState(
        targetValue = if (checked) 0f else 1f,
        animationSpec = tween(
            durationMillis = IOSwitchAnimation.SWITCHER_DURATION,
            easing = IOBounceEasing(
                amplitude = if (checked) {
                    IOSwitchAnimation.BOUNCE_AMPLITUDE_OUT
                } else {
                    IOSwitchAnimation.BOUNCE_AMPLITUDE_IN
                },
                frequency = if (checked) {
                    IOSwitchAnimation.BOUNCE_FREQUENCY_OUT
                } else {
                    IOSwitchAnimation.BOUNCE_FREQUENCY_IN
                }
            )
        ),
        label = "iconProgress"
    )

    val translationProgress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(
            durationMillis = IOSwitchAnimation.TRANSLATE_DURATION,
            easing = LinearEasing
        ),
        label = "translationProgress"
    )

    return IOSwitchAnimationState(
        iconProgress = iconProgress,
        translationProgress = when (type) {
            IOSwitchType.CAPSULE -> translationProgress
            IOSwitchType.PILL -> 0f
        },
        clickOffset = clickOffset
    )
}
