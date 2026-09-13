package io.iprashantpanwar.ioswitch.drawing

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import io.iprashantpanwar.ioswitch.animation.IOSwitchAnimationState
import io.iprashantpanwar.ioswitch.animation.lerp

/**
 * Renders the horizontal capsule IOSwitch.
 *
 * This renderer contains the geometry from the current IO Switch
 * implementation, including:
 *
 * - Right-origin thumb calculation.
 * - Thumb morphing.
 * - Independent horizontal translation.
 * - Tap compression.
 * - Background clipping.
 *
 * @param backgroundColor Current animated track color.
 * @param iconColor Thumb color.
 * @param animationState Current animation state.
 */
internal fun DrawScope.drawCapsuleSwitch(
    backgroundColor: Color,
    iconColor: Color,
    animationState: IOSwitchAnimationState,
) {
    val iconProgress = animationState.iconProgress
    val translationProgress = animationState.translationProgress
    val clickOffset = animationState.clickOffset

    val shadowOffset = 0f

    val switcherCornerRadius =
        (size.height - shadowOffset * 2f) / 2f

    /*
     * ---------------------------------------------------------
     * Switch bounds
     * ---------------------------------------------------------
     */
    val switcherLeft =
        clickOffset + shadowOffset

    val switcherTop =
        clickOffset + shadowOffset / 2f

    val switcherRight =
        size.width -
                clickOffset -
                shadowOffset

    val switcherBottom =
        size.height -
                clickOffset -
                shadowOffset -
                shadowOffset / 2f

    /*
     * ---------------------------------------------------------
     * Thumb geometry
     * ---------------------------------------------------------
     */
    val iconRadius =
        switcherCornerRadius * 0.6f

    val iconClipRadius =
        iconRadius / 2.25f

    val iconCollapsedWidth =
        iconRadius - iconClipRadius

    val iconHeight =
        iconRadius * 2f

    val iconOffset = lerp(
        start = 0f,
        stop =
            iconRadius -
                    iconCollapsedWidth / 2f,
        fraction = iconProgress
    )

    /*
     * The thumb is calculated from the right side first.
     *
     * This is important because it is what allows the morph and
     * translation animations to remain independent.
     */
    val iconLeft =
        size.width -
                switcherCornerRadius -
                iconCollapsedWidth / 2f -
                iconOffset

    val iconRight =
        size.width -
                switcherCornerRadius +
                iconCollapsedWidth / 2f +
                iconOffset

    val iconTop =
        ((size.height - iconHeight) / 2f) -
                shadowOffset / 2f

    val iconBottom =
        iconTop + iconHeight

    val iconWidth =
        iconRight - iconLeft

    /*
     * ---------------------------------------------------------
     * Morph geometry
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
        (iconTop + iconBottom) / 2f

    /*
     * ---------------------------------------------------------
     * Thumb translation
     * ---------------------------------------------------------
     */
    val iconTranslateStart =
        -(
                size.width -
                        shadowOffset -
                        switcherCornerRadius * 2f
                )

    val iconTranslateEnd =
        -shadowOffset

    val iconTranslateX = lerp(
        start = iconTranslateStart,
        stop = iconTranslateEnd,
        fraction = translationProgress
    )

    /*
     * ---------------------------------------------------------
     * Background
     * ---------------------------------------------------------
     */
    drawRoundRect(
        color = backgroundColor,
        topLeft = Offset(
            switcherLeft,
            switcherTop
        ),
        size = Size(
            width =
                switcherRight -
                        switcherLeft,
            height =
                switcherBottom -
                        switcherTop
        ),
        cornerRadius = CornerRadius(
            switcherCornerRadius,
            switcherCornerRadius
        )
    )

    /*
     * ---------------------------------------------------------
     * Thumb
     * ---------------------------------------------------------
     */
    drawRoundRect(
        color = iconColor,
        topLeft = Offset(
            iconLeft + iconTranslateX,
            iconTop
        ),
        size = Size(
            iconWidth,
            iconHeight
        ),
        cornerRadius = CornerRadius(
            switcherCornerRadius,
            switcherCornerRadius
        )
    )

    /*
     * ---------------------------------------------------------
     * Morphing clip
     * ---------------------------------------------------------
     */
    if (clipWidth > iconCollapsedWidth) {
        drawRoundRect(
            color = backgroundColor,
            topLeft = Offset(
                iconCenterX -
                        clipOffset +
                        iconTranslateX,
                iconCenterY -
                        clipOffset
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
