# MusicAlbums

A sample Android app that displays the top 100 music
albums across all genres using Apple’s RSS generator found here:
https://rss.applemarketingtools.com/.

## Video Demo

[Watch the video demo](https://drive.google.com/file/d/1qtGAVTLV3GCJQEyMaDvATfd4fm_GvPXX/view?usp=sharing)

## Architecture

This project follows the Clean Architecture principles with the MVVM (Model-View-ViewModel) pattern in the presentation layer. Clean Architecture allows for a separation of concerns, making the codebase more maintainable, testable, and scalable. The MVVM pattern helps in organizing the code and provides a clear separation between the UI and business logic.

## Tech Stack

- **100% [Kotlin](https://kotlinlang.org/)**: Utilized for all aspects of the application.
- **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)**: Used for managing asynchronous tasks and data streams efficiently.
- **[Hilt](https://dagger.dev/hilt/)**: Integrated for dependency injection, making the code more modular and easier to test.
- **MVVM with state management**: Ensures a clear separation between the UI and the business logic.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose/)**: Utilized for building the UI declaratively.
- **[Navigation with Compose](https://developer.android.com/develop/ui/compose/navigation)**: Handles the navigation logic within the app.
- **Room Persistence**: Constructs a local database to cache data, improving the app's performance and offline capabilities.
- **[Retrofit2 & OkHttp3](https://github.com/square/retrofit)**: Constructs the REST APIs for network communication.
- **[Gson](https://github.com/google/gson)**: Simplifies JSON parsing into Kotlin objects.
- **[Coil](https://github.com/coil-kt/coil)**: Used for efficient image loading and caching.

## Design Decisions

1. **Clean Architecture**:
  - Chosen for its ability to separate concerns and create a scalable and testable codebase.
  - Layers include Presentation, Domain, and Data, each with distinct responsibilities.

2. **MVVM Pattern**:
  - Enhances separation of concerns by decoupling the UI from the business logic.
  - ViewModel manages the UI-related data, making the UI code cleaner and more maintainable.

3. **Jetpack Compose**:
  - Selected for its modern approach to building UIs, which is more intuitive and concise compared to XML-based layouts.
  - Allows for reactive programming, where the UI automatically updates when the underlying data changes.

4. **Hilt for Dependency Injection**:
  - Simplifies the process of providing dependencies, making the code more modular and easier to test.

5. **Coroutines and Flow**:
  - Utilized for their ability to handle asynchronous tasks and manage data streams efficiently.
  - Coroutines provide a way to write asynchronous code that is sequential and easy to understand.

## Features

### Top Albums
- Displays a list of top albums retrieved from a remote source.

### Album Details
- Provides detailed information about a selected album.
- Allows users to go to iTunes store.

## Code Documentation

### Folder Structure

- **data**: Contains data sources, repositories, and models.
- **domain**: Includes use cases and domain models.
- **presentation**: Holds UI-related classes such as Activities, Fragments, ViewModels, and Compose UI components.
- **di**: Contains dependency injection setup and modules.

### Notable Classes and Functions

- **AlbumsRepository**:
  - Responsible for fetching data from the remote API and local database.

- **GetTopAlbumsUseCase**:
  - A use case that encapsulates the logic for retrieving the top albums.

- **AlbumsViewModel**:
  - Manages the state of the UI, interacts with use cases, and provides data to the UI components.

- **TopAlbumsScreen**:
  - A Composable function that displays the list of top albums.

- **AlbumDetailsScreen**:
  - A Composable function that displays detailed information about a selected album.

## Getting Started

### Prerequisites

- Android Studio Bumblebee or higher.
- Kotlin 1.5 or higher.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/MohamedE610/MusicAlbums.git

2. Open the project in Android Studio.
3. Build the project to install dependencies.

### Running the App

1. Connect an Android device or start an emulator.
2. Run the project from Android Studio.

### Contributing

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/YourFeature

3. Commit your changes:
   ```bash
   git commit -m 'Add some feature'

4. Push to the branch:
   ```bash
   git push origin feature/YourFeature
    
5. Open a pull request.

