# 🎬 MovieLeak

A modern Android application for discovering and exploring movies, built with Kotlin using Clean Architecture principles and the latest Android development practices.

## 📱 Features
- **Movie Search**: Search for movies by title
- **Movie Details**: View comprehensive movie information including:
  - Synopsis and plot details
  - Runtime and release information
  - Movie posters and artwork
  - Cast and crew information
  - Watch trailers
- **Clean UI**: Material Design with intuitive navigation

## 🏗️ Architecture

This project follows **Clean Architecture** principles with **MVVM** pattern:

```
├── data/                   # Data layer
│   ├── api/               # Network API services
│   ├── mapper/            # Data mappers
│   ├── model/             # Data models
│   ├── repository/        # Repository implementations
│   └── response/          # API response models
├── domain/                # Business logic layer
│   ├── model/             # Domain models
│   └── usecase/           # Use cases
└── presentation/          # Presentation layer
    ├── ui/                # Fragments and UI components
    ├── adapter/           # RecyclerView adapters
    ├── viewmodel/         # ViewModels
    └── utils/             # UI utilities
```

## 🛠️ Tech Stack

### Core
- **Kotlin** - Primary programming language
- **Android SDK** - Target SDK 36, Min SDK 26
- **View Binding** - Type-safe view references

### Architecture & DI
- **Hilt** - Dependency injection
- **MVVM** - Architectural pattern
- **Clean Architecture** - Project structure
- **Navigation Component** - Fragment navigation with Safe Args

### Networking
- **Retrofit** - HTTP client
- **Moshi** - JSON parsing
- **OkHttp Interceptor** - Network logging and API key injection

### UI & Images
- **Material Design Components** - UI components
- **Coil** - Image loading and caching
- **ConstraintLayout** - Flexible layouts

### Development & Debugging
- **Timber** - Logging
- **KSP** - Kotlin Symbol Processing

### Testing
- **JUnit** - Unit testing
- **Mockito** - Mocking framework
- **AndroidX Test** - Android testing
- **Espresso** - UI testing
- **Coroutines Test** - Async testing

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Hedgehog | 2023.1.1 or later
- **JDK 21** or higher
- **Android SDK** with API level 36
- **TMDb API Key** (The Movie Database)

### Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dapascript/MovieLeak.git
   cd MovieLeak
   ```

2. **Get TMDb API Key**:
   - Visit [The Movie Database (TMDb)](https://www.themoviedb.org/)
   - Create an account and request an API key
   - Add your API key to the project (see Configuration section)

3. **Open in Android Studio**:
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned repository

4. **Sync and Build**:
   - Let Android Studio sync the project
   - Build the project (`Build > Make Project`)

5. **Run the app**:
   - Connect an Android device or start an emulator
   - Click the "Run" button or press `Shift + F10`

## 🤝 Contributing

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add some amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Code Style

- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Add comments for complex logic
- Write tests for new features

## 📄 API

This app uses [The Movie Database (TMDb) API](https://www.themoviedb.org/documentation/api) to fetch movie data:

- **Popular Movies**: `/movie/popular`
- **Search Movies**: `/search/movie`
- **Movie Details**: `/movie/{movie_id}`
- **Movie Credits**: `/movie/{movie_id}/credits`
- **Movie Videos**: `/movie/{movie_id}/videos`

## 📧 Contact

**Developer**: dapascript  
**Email**: [daffaprabowo5@example.com]

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [The Movie Database (TMDb)](https://www.themoviedb.org/) for providing the movie data API
- [Material Design](https://material.io/) for design guidelines
- Android development community for inspiration and best practices

---

<p align="center">Made with ❤️ Daffa</p>
