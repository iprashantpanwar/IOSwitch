package io.iprashantpanwar.ioswitch.drawing

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import io.iprashantpanwar.ioswitch.animation.IOSwitchAnimationState
import io.iprashantpanwar.ioswitch.animation.lerp
import kotlin.math.min

/**
 * Renders the circular pill IOSwitch.
 *
 * This implementation follows the geometry, treating the pill as a capsule
 * with different corner radii.
 *
 * The track is circular and the thumb remains centered while
 * morphing between its expanded and collapsed states.
 *
 * @param backgroundColor Current animated track color.
 * @param iconColor Thumb color.
 * @param animationState Current animation state.
 */
internal fun DrawScope.drawPillSwitch(
    backgroundColor: Color,
    iconColor: Color,
    animationState: IOSwitchAnimationState,
) {
    val iconProgress = animationState.iconProgress
    val clickOffset = animationState.clickOffset

    /*
     * Pill uses the smallest dimension as its radius.
     */
    val baseSize = min(
        size.width,
        size.height
    )

    val switcherRadius =
        baseSize / 2f

    /*
     * Tap compression is applied around the circular body.
     */
    val radius =
        switcherRadius - clickOffset

    val center = Offset(
        x = size.width / 2f,
        y = size.height / 2f
    )

    /*
     * ---------------------------------------------------------
     * Thumb geometry
     * ---------------------------------------------------------
     */
    val iconRadius =
        radius * 0.5f

    val iconClipRadius =
        iconRadius / 2.25f

    val iconCollapsedWidth =
        (iconRadius - iconClipRadius) * 1.1f

    val iconHeight =
        iconRadius * 2f

    /*
     * ---------------------------------------------------------
     * Thumb morph
     * ---------------------------------------------------------
     */
    val iconOffset = lerp(
        start = 0f,
        stop =
            iconRadius -
                    iconCollapsedWidth / 2f,
        fraction = iconProgress
    )

    val iconLeft =
        center.x -
                iconCollapsedWidth / 2f -
                iconOffset

    val iconRight =
        center.x +
                iconCollapsedWidth / 2f +
                iconOffset

    val iconTop =
        center.y -
                iconHeight / 2f

    val iconWidth =
        iconRight - iconLeft

    /*
     * ---------------------------------------------------------
     * Morphing clip
     * ---------------------------------------------------------
     */
    val clipOffset = lerp(
        start = 0f,
        stop = iconClipRadius,
        fraction = iconProgress
    )

    val clipWidth =
        clipOffset * 2f

    val iconCenterX =
        (iconLeft + iconRight) / 2f

    val iconCenterY =
        iconTop +
                iconHeight / 2f

    /*
     * ---------------------------------------------------------
     * Circular background
     * ---------------------------------------------------------
     */
    drawCircle(
        color = backgroundColor,
        radius = radius,
        center = center
    )

    /*
     * ---------------------------------------------------------
     * Thumb
     * ---------------------------------------------------------
     */
    drawRoundRect(
        color = iconColor,
        topLeft = Offset(
            iconLeft,
            iconTop
        ),
        size = Size(
            iconWidth,
            iconHeight
        ),
        cornerRadius = CornerRadius(
            iconRadius,
            iconRadius
        )
    )

    /*
     * ---------------------------------------------------------
     * Morphing clip
     * ---------------------------------------------------------
     *
     * The clip is omitted once the thumb has
     * collapsed enough that drawing it would create an unwanted
     * small circle.
     */
    if (clipWidth > iconCollapsedWidth) {
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(
                iconCenterX - clipOffset,
                iconCenterY - clipOffset
            ),
            size = Size(
                clipWidth,
                clipWidth
            ),
            cornerRadius = CornerRadius(
                iconRadius,
                iconRadius
            )
        )
    }
}
