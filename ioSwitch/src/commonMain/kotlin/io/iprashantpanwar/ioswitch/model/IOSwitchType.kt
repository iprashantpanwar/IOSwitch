package io.iprashantpanwar.ioswitch.model

import androidx.compose.foundation.shape.RoundedCornerShape

/**
 * Defines the visual shape of an [io.iprashantpanwar.ioswitch.IOSwitch].
 *
 * [CAPSULE] is the horizontal switch style
 *
 * [PILL] is the circular switch style
 */
enum class IOSwitchType {

    /**
     * A horizontal capsule-shaped switch with a sliding thumb.
     */
    CAPSULE,

    /**
     * A circular switch with a morphing thumb.
     */
    PILL,
}

/**
 * Returns the aspect ratio associated with an IOSwitch type.
 */
internal val IOSwitchType.aspectRatio: Float
    get() = when(this) {
        IOSwitchType.PILL -> 1f
        IOSwitchType.CAPSULE -> 64f / 36f
    }
