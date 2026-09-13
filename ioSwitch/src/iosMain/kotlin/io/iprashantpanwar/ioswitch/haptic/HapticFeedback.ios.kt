package io.iprashantpanwar.ioswitch.haptic

import platform.UIKit.UISelectionFeedbackGenerator

internal actual fun performIOSwitchHapticFeedback() {
    val generator = UISelectionFeedbackGenerator()

    generator.prepare()
    generator.selectionChanged()
}