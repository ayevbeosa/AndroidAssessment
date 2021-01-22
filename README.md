# AndroidAssessment
An Android Application that pulls subject/media content information from an API and displays them.

Major features of this app are:

- Offline first
- MVVM Architecture and separation of concerns
- Dependency Injection
- Video Player

App Design is gotten from [here](https://www.figma.com/file/rfaVkZMsmdq69sioOSuvfN/Android-Interview-App?node-id=0%3A1) and the API from [here](https://jackiechanbruteforce.ulesson.com/3p/api/content/grade)

# Development Setup 🖥

The app was developed using 
- Android Studio 4.1.1
- Gradle 4.1.1
- Android API Level 30
- Kotlin 1.4.21

## Libraries and tools 🛠

The App uses several modern libraries and tools used to achieve its purpose

- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
- [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
- [Dagger 2](https://dagger.dev/dev-guide/)
- [Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- Other [Android Jetpack](https://developer.android.com/jetpack) components

## Architecture

App uses a Single Activity thereby making use of the rich and modular Fragments. Each Fragment holds the view for each screen as in the UI designs
Creation, recreation and navigation of Fragments and between Fragments is handled by Navigation Component of Android Jetpack. The simplifies interaction
and passing of data between Views. Offline capabilities is powered by Room (part of Android Architecture Components), the local data uses a One-to-Many relationship
to relate the different data models. The local data is more or less the single source of truth (which differs in structure from the data fetched remotely).
The app gets all data from a Repository class which does the pulling of data both remotely and locally.

The app follows the Model-View-ViewModel (MVVM) pattern represented below;

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)