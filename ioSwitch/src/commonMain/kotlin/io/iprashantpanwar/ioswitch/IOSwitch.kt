package io.iprashantpanwar.ioswitch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.iprashantpanwar.ioswitch.animation.IOSwitchAnimation
import io.iprashantpanwar.ioswitch.animation.rememberIOSwitchAnimationState
import io.iprashantpanwar.ioswitch.drawing.IOSwitchRenderer
import io.iprashantpanwar.ioswitch.interaction.IOSwitchInteraction
import io.iprashantpanwar.ioswitch.layout.resolveIOSwitchSize
import io.iprashantpanwar.ioswitch.model.IOSwitchColors
import io.iprashantpanwar.ioswitch.model.IOSwitchDefaults
import io.iprashantpanwar.ioswitch.model.IOSwitchShadow
import io.iprashantpanwar.ioswitch.model.IOSwitchType

/**
 * A customizable, animated switch for Compose Multiplatform.
 *
 * IOSwitch supports two visual variants:
 *
 * - [IOSwitchType.CAPSULE] — the horizontal morphing switch.
 * - [IOSwitchType.PILL] — the circular morphing switch.
 *
 * The capsule variant preserves the current IO Switch behavior,
 * including its independent thumb morph and translation animations.
 *
 *
 * Example:
 *
 * ```
 * var checked by remember { mutableStateOf(false) }
 *
 * IOSwitch(
 *     checked = checked,
 *     onCheckedChange = { checked = it }
 * )
 * ```
 *
 * Pill:
 *
 * ```
 * IOSwitch(
 *     checked = checked,
 *     onCheckedChange = { checked = it },
 *     type = IOSwitchType.PILL,
 *     width = 40.dp,
 *     height = 40.dp
 * )
 * ```
 *
 * @param checked Current checked state.
 * @param onCheckedChange Called when the switch is toggled.
 * @param modifier Modifier applied to the switch.
 * @param type Visual shape of the switch.
 * @param width Width of the switch.
 * @param height Height of the switch.
 * @param colors Colors used for the switch.
 * @param shadow Shadow used to draw behind the switch.
 * @param hapticFeedback Whether haptic feedback should be performed
 * when toggling the switch.
 */
@Composable
fun IOSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    type: IOSwitchType = IOSwitchType.PILL,
    colors: IOSwitchColors = IOSwitchDefaults.colors(),
    shadow: IOSwitchShadow = IOSwitchDefaults.shadow(),
    width: Dp = 64.dp,
    height: Dp = 36.dp,
    hapticFeedback: Boolean = true,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit = { },
) {
    /*
     * Resolve the dimensions before applying any visual or
     * interaction modifiers.
     *
     * This is important because shadow and ripple operate on the
     * layout bounds, not on the pixels drawn by the renderer.
     */
    val switchSize = resolveIOSwitchSize(
        type = type,
        width = width,
        height = height,
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (checked) {
            colors.checkedTrackColor
        } else {
            colors.uncheckedTrackColor
        },
        animationSpec = tween(
            durationMillis = IOSwitchAnimation.COLOR_DURATION
        ),
        label = "backgroundColor"
    )

    val shadowColor = shadow.color ?: backgroundColor.copy(
        alpha = shadow.alpha
    )

    val clickOffset = remember {
        Animatable(0f)
    }

    val animationState =
        rememberIOSwitchAnimationState(
            checked = checked,
            type = type,
            clickOffset = clickOffset.value
        )
    val shape = RoundedCornerShape(percent = 50)

    IOSwitchInteraction(
        modifier = modifier
            .size(switchSize)
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    radius = shadow.radius,
                    spread = shadow.spread,
                    color = shadowColor,
                    offset = shadow.offset
                )
            )
            .clip(shape),
        enabled = enabled,
        hapticFeedback = hapticFeedback,
        clickOffset = clickOffset,
        onClick = {
            onCheckedChange(!checked)
        }
    ) {
        IOSwitchRenderer(
            type = type,
            backgroundColor = backgroundColor,
            iconColor = colors.thumbColor,
            animationState = animationState
        )
    }
}
