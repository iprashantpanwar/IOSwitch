package io.github.iprashantpanwar.composeApp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.iprashantpanwar.ioswitch.IOSwitch
import io.iprashantpanwar.ioswitch.model.IOSwitchType
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var checked by remember {
            mutableStateOf(false)
        }

        Surface(
            color = Color(0xFFDADADA)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    IOSwitch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    IOSwitch(
                        checked = checked,
                        type = IOSwitchType.CAPSULE,
                        onCheckedChange = { checked = it },
                    )
                }
            }
        }
    }
}