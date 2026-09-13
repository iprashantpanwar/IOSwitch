package io.iprashantpanwar.ioswitch.haptic

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext

internal lateinit var ioswitchContext: Context

internal actual fun performIOSwitchHapticFeedback() {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = ioswitchContext.getSystemService(
            Context.VIBRATOR_MANAGER_SERVICE
        ) as VibratorManager

        manager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        ioswitchContext.getSystemService(
            Context.VIBRATOR_SERVICE
        ) as Vibrator
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        vibrator.vibrate(
            VibrationEffect.createPredefined(
                VibrationEffect.EFFECT_TICK
            )
        )
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @Suppress("DEPRECATION")
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    10L,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        }
    }
}

@Composable
internal fun rememberIOSwitchContext() {
    val context = LocalContext.current

    SideEffect {
        ioswitchContext = context.applicationContext
    }
}