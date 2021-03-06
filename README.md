
# CryptoMovies
CryptoMovies is a small app that shows modern Android developement: with Hilt, Coroutines, Flow, Jetpack and Material Design 3.
![Logo](https://i.ibb.co/S0mY0KZ/Senza-titolo-1.png)
## Project Setup / Before building the app
Is a bad practice storing API Keys inside Git repositories so you will need to add it manually. Please, add the MovieDB API Key inside the `local.properties` file in the root directory of the project: 
```
sdk.dir=/Users/lpirro/Library/Android/sdk
apiKey="moviedb api key"
```
## Tech stack

- Minimum SDK 23
- 100% Kotlin
- [Material Design 3](https://m3.material.io)
- Dark/Light mode support
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations
- [HILT](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection
- Architecture
  - MVVM 
  - Clean Architecture 
  - Repository Pattern
- Jetpack
    - [Navigation](https://developer.android.com/guide/navigation): For handling Navigation inside the app
    - [LifeCycle](https://developer.android.com/topic/libraries/architecture/lifecycle): For managing UI related data in a LifeCycle conscious way
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding): For binding UI views into controllers (like Fragments, Activities)
    - [Room](https://developer.android.com/training/data-storage/room): For creating a Database by providing an abstraction layer over SQLite 
- [Retrofit / OkHttp3](https://github.com/square/retrofit): For performing network request
- [Robolectric](http://robolectric.org): For Unit Test
- [Mockito](https://site.mockito.org): Mocking framework for Unit Test
- [Turbine](https://github.com/cashapp/turbine): Turbine is a small testing library for kotlinx.coroutines Flow.
- [Glide](https://bumptech.github.io/glide/): For network image loading
- Material Components: For building the UI 
- [Ktlint](https://ktlint.github.io): For code-formatting and for keeping the code style consistent across the project
## Architecture
This app is based on MVVM architecture and follows Clean Architecture principles with the repository pattern

![Architecture Diagram](https://i.ibb.co/nz3hvnY/final-002.png)
## Design
![Logo](https://i.ibb.co/6BqqNFR/figma.png)

For designing this app, I used Figma, one of the industry standard tools used by Designers for creating UI and UX for mobile and Desktop. You can find the artboard of the app with all the specs here: [CryptoMovies](https://www.figma.com/file/vH2ODv1osOVA4UyUVQY4tQ/CryptoMovies?)
## API
CryptoMovies uses the open API from [The Movie Database](https://developers.themoviedb.org/3)
