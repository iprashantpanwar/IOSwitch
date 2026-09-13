This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM).

* [/composeApp](./composeApp) contains the main application code for all platforms. It uses Compose Multiplatform to share UI logic.
* [/iosApp](./iosApp/iosApp) contains the iOS application entry point and SwiftUI code.
* [/ioSwitch](./ioSwitch/src) is a shared library module used by the applications.
  - [commonMain](./ioSwitch/src/commonMain/kotlin) is for code that’s common for all targets.
  - Platform-specific folders (e.g., [iosMain](./ioSwitch/src/iosMain/kotlin)) are for platform-dependent implementations.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands:

- **Android app**: `./gradlew :composeApp:assembleDebug`
- **Desktop app**:
  - Hot reload: `./gradlew :composeApp:hotRunJvm --auto`
  - Standard run: `./gradlew :composeApp:jvmRun`
- **Web app**:
  - Wasm target (faster, modern browsers): `./gradlew :composeApp:wasmJsBrowserDevelopmentRun`
  - JS target (slower, supports older browsers): `./gradlew :composeApp:jsBrowserDevelopmentRun`
- **iOS app**: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- **Android tests**: `./gradlew :composeApp:testDebugUnitTest`
- **Desktop tests**: `./gradlew :composeApp:jvmTest`
- **Web tests**:
  - Wasm target: `./gradlew :composeApp:wasmJsBrowserTest`
  - JS target: `./gradlew :composeApp:jsBrowserTest`
- **iOS tests**: `./gradlew :composeApp:iosSimulatorArm64Test`
- **Shared library tests**: `./gradlew :ioSwitch:allTests`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).
