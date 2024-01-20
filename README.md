# DogsAppCMP
Browse dogs in a Compose multiplatform app.
KTOR, Voyager, Kamel, KMMViewmodel, Coroutines

TODO: WASM (stuck on library dependencies)
This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop.

## Screenshots
<table>
  <tr>Android</tr>
  <tr>
    <td><img src="https://raw.githubusercontent.com/pa1pal/DogsAppCMP/main/screenshots/android.png"></td>
    <td><img src="https://raw.githubusercontent.com/pa1pal/DogsAppCMP/main/screenshots/android2.png"></td>
  </tr>
  </table>
  <table>
  <tr>iOS</tr>
  <tr>
     <td><img src="https://raw.githubusercontent.com/pa1pal/DogsAppCMP/main/screenshots/ios.png"></td>
     <td><img src="https://raw.githubusercontent.com/pa1pal/DogsAppCMP/main/screenshots/ios2.png"></td>
  </tr>
  </table>
  <table>
  <tr>Desktop</tr>
  <tr>
      <td><img src="https://raw.githubusercontent.com/pa1pal/DogsAppCMP/main/screenshots/desktop1.png"></td>
      <td><img src="https://raw.githubusercontent.com/pa1pal/DogsAppCMP/main/screenshots/desktop2.png"></td>
    </tr>
</table>


* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…
