package io.iprashantpanwar.ioswitch.animation

import io.iprashantpanwar.ioswitch.IOSwitch

/**
 * Contains the animation constants used by [IOSwitch].
 *
 * These values are kept together so the animation implementation can
 * be tuned without modifying the switch API or drawing code.
 */
internal object IOSwitchAnimation {

    /**
     * Duration of the thumb morph animation.
     */
    const val SWITCHER_DURATION = 800

    /**
     * Duration of the track color transition.
     */
    const val COLOR_DURATION = 300

    /**
     * Duration of the horizontal thumb translation.
     */
    const val TRANSLATE_DURATION = 200

    /**
     * Amplitude used when animating from the checked state.
     */
    const val BOUNCE_AMPLITUDE_OUT = 0.15

    /**
     * Amplitude used when animating to the unchecked state.
     */
    const val BOUNCE_AMPLITUDE_IN = 0.2

    /**
     * Frequency used when animating from the checked state.
     */
    const val BOUNCE_FREQUENCY_OUT = 12.0

    /**
     * Frequency used when animating to the unchecked state.
     */
    const val BOUNCE_FREQUENCY_IN = 14.5

    /**
     *
     */
    const val ON_CLICK_RADIUS_OFFSET = 4f

    /**
     *
     */
    const val TRANSLATE_ANIMATION_DURATION = 200
}
