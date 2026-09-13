package io.iprashantpanwar.ioswitch.animation

import androidx.compose.animation.core.Easing
import kotlin.math.cos
import kotlin.math.exp

/**
 * Easing function use to thumb morph animation.
 *
 * The easing combines exponential decay with a cosine wave to
 * produce the characteristic spring-like deformation of the
 * switch thumb.
 *
 * @param amplitude Controls the decay of the oscillation.
 * @param frequency Controls the frequency of the oscillation.
 */
internal class IOBounceEasing(
    private val amplitude: Double,
    private val frequency: Double,
) : Easing {

    /**
     * Transforms a normalized animation fraction.
     *
     * @param fraction Animation fraction from `0f` to `1f`.
     * @return Transformed animation fraction.
     */
    override fun transform(
        fraction: Float
    ): Float {
        val t = fraction.toDouble()

        return (
            -exp(-t / amplitude) *
                cos(frequency * t) +
                1.0
            ).toFloat()
    }
}