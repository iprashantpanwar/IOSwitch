package io.iprashantpanwar.ioswitch.interaction

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import io.iprashantpanwar.ioswitch.animation.IOSwitchAnimation
import io.iprashantpanwar.ioswitch.haptic.performIOSwitchHapticFeedback
import kotlinx.coroutines.launch
import io.iprashantpanwar.ioswitch.IOSwitch

/**
 * Provides the interaction behavior for [IOSwitch].
 *
 * This component intentionally contains no switch drawing or
 * animation logic. Its only responsibility is translating a user
 * interaction into the switch's click callback.
 *
 * Keeping interaction separate prevents the public switch composable
 * from becoming coupled to Compose's clickable implementation.
 *
 * @param modifier Modifier used for the interactive surface.
 * @param onClick Called when the switch is tapped.
 * @param content Content rendered inside the interactive surface.
 * @param hapticFeedback Whether to perform a haptic feedback
 * @param onClick Called when the switch is tapped.
 * @param enabled Controls the enabled state of the switch.
 */
@Composable
internal fun IOSwitchInteraction(
    modifier: Modifier,
    clickOffset: Animatable<Float, AnimationVector1D>,
    enabled: Boolean,
    hapticFeedback: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val interactionSource =
        remember { MutableInteractionSource() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(percent = 50))
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = ripple(),
                onClick = {
                    if (hapticFeedback) {
                        performIOSwitchHapticFeedback()
                    }

                    coroutineScope.launch {
                        clickOffset.snapTo(
                            IOSwitchAnimation.ON_CLICK_RADIUS_OFFSET
                        )

                        clickOffset.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(
                                durationMillis =
                                    IOSwitchAnimation.TRANSLATE_ANIMATION_DURATION,
                                easing = LinearEasing
                            )
                        )
                    }


                    onClick()
                }
            )
    ) {
        content()
    }
}