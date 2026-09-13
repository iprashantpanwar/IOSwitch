package io.github.iprashantpanwar.composeApp

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import platform.UIKit.UIColor

class ViewControllerFactory {
    fun create(): UIViewController = mainViewController()
}

fun mainViewController(): UIViewController = ComposeUIViewController {
    App()
}.apply {
    view.backgroundColor = UIColor.whiteColor
}
