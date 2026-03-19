# 📰 DailyPulse

A Kotlin Multiplatform app for browsing daily news articles.
This project focuses on **sharing business logic across Android and iOS** while keeping native UI on each platform — built as part of my journey to explore Kotlin Multiplatform development.

---

## ✨ Features

- 📰 **Articles Feed** — Browse a list of daily news articles.
- 🤝 **Shared Domain & Data** — Business logic, use cases, and data sources are shared between Android and iOS via a common Kotlin module.
- 📱 **Native Android UI** — Built with Jetpack Compose following MVI architecture.
- 🍎 **Native iOS UI** — Built with SwiftUI.

---

## 🏗 Architecture

This app follows **Clean Architecture** principles with a clear separation between layers, targeting both Android and iOS through **Kotlin Multiplatform**.

### Kotlin Multiplatform

- **`shared/`** — Common Kotlin module compiled for both Android and iOS. Contains domain models, use cases, and repository interfaces.
- **`androidApp/`** — Android-only UI module using Jetpack Compose and MVI.
- **`iosApp/`** — Native iOS app in Swift consuming the shared framework.

### MVI (Android)

- **Intent** — User events dispatched from the UI.
- **ViewModel** — Maps intents to actions, updates state, and triggers effects.
- **State** — Immutable UI state rendered by Compose.

### Clean Architecture Layers

- **Domain** — Entities, use cases, repository interfaces (in `shared/commonMain`)
- **Data** — Repository implementations, remote/local data sources (in `shared`)
- **Presentation** — ViewModels, Intents, States, Effects (in `androidApp` / `iosApp`)

---

## 🔧 Tech Stack

- 🟣 **Kotlin Multiplatform** — Shared business logic across platforms
- 🖌️ **Jetpack Compose** — Android UI
- 🍎 **SwiftUI** — iOS UI
- 🏗️ **MVI** — Android presentation pattern
- 🧩 **Hilt** — Dependency injection (Android)
- 🏛️ **Clean Architecture** — Separation of concerns

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio Hedgehog+** with Kotlin Multiplatform plugin
- **Xcode 15+** (for iOS)

### Android

Clone the repository and open it in Android Studio, then run the `androidApp` configuration.

Or from the terminal:
```bash
./gradlew :androidApp:assembleDebug
```

### iOS

Open the `iosApp/iosApp.xcodeproj` in Xcode, select a simulator, and hit Run.

---

## 👥 Author

**Matias Di Russo**

- LinkedIn: [Matias Di Russo](https://www.linkedin.com/in/matias-di-russo-2870b54b)

---

> ✅ *Built with passion as part of my Kotlin Multiplatform learning journey!*