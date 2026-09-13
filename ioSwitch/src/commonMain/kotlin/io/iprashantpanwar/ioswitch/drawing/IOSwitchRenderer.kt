package io.iprashantpanwar.ioswitch.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.iprashantpanwar.ioswitch.animation.IOSwitchAnimationState
import io.iprashantpanwar.ioswitch.model.IOSwitchType

/**
 * Renders an IOSwitch using the renderer associated with its shape.
 *
 * This component does not contain shape-specific geometry. It only
 * selects between the capsule and pill renderers.
 *
 * @param type Visual shape of the switch.
 * @param backgroundColor Current animated track color.
 * @param iconColor Thumb color.
 * @param animationState Current animation state.
 */
@Composable
internal fun IOSwitchRenderer(
    type: IOSwitchType,
    backgroundColor: Color,
    iconColor: Color,
    animationState: IOSwitchAnimationState,
) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        when (type) {
            IOSwitchType.CAPSULE -> {
                drawCapsuleSwitch(
                    backgroundColor = backgroundColor,
                    iconColor = iconColor,
                    animationState = animationState
                )
            }

            IOSwitchType.PILL -> {
                drawPillSwitch(
                    backgroundColor = backgroundColor,
                    iconColor = iconColor,
                    animationState = animationState
                )
            }
        }
    }
}
