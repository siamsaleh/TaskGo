# Mid-Level Android Developer Takeaway Task

[![Download APK](https://img.shields.io/badge/Download-APK-brightgreen)](https://github.com/siamsaleh/TaskGo/raw/refs/heads/master/TaskGo.apk)
[![App Demo](https://img.shields.io/badge/Watch-Demo-blue)](https://www.youtube.com/shorts/LRDV8Suqxsk)

[![Video Demo](https://img.youtube.com/vi/LRDV8Suqxsk/0.jpg)]([https://www.youtube.com/watch?v=A8NOCuv97DE](https://youtube.com/shorts/LRDV8Suqxsk?feature=share))

## Objective:
This project is developed to replicate the UI and functionality as per the provided Figma design, with API integration for real-world use cases. The application follows a clean architecture and employs good design patterns and UI/UX principles, including Material Design, Hilt for dependency injection, and MVVM clean architecture.

## Project Overview:
The mobile application closely replicates the design from the [Figma file](https://www.figma.com/design/ZvDf0vD3tPZnJgwrVtUqBL/Test-Project?node-id=0-1&t=IzP7Swiic0QBSx6N-1), focusing on the following main areas:
- **UI Design Replication**: Accurate layout, fonts, colors, and elements as per Figma.
- **API Integration**: Use the provided API to integrate recommended place and detail sections functionality.

## Features:
- **UI Design**: Implemented according to the Figma design.
- **Recommended List**: Integrated API for the recommended list.
- **Detail Section**: API integrated to display detailed information.

## Technologies Used:
- **Programming Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Libraries**: Retrofit, Glide, ViewModel, LiveData, etc.
- **UI Components**: RecyclerView, CardView, ConstraintLayout, etc.

## Why Use Separate Adapters?  

Even with the same features and functionality, separate adapters provide better clarity and maintainability by isolating the logic for different list types. This separation avoids mixing layout-specific behaviors (e.g., horizontal vs. vertical orientation), reduces the risk of introducing errors when modifying one list, and keeps the codebase clean and modular. It ensures that future changes to one layout's design or functionality can be implemented without affecting the other, promoting scalability and adherence to best practices.

## API Integration:
API used: [Mockbin API](9133e3e4055644b890c2ca078f3163ad.api.mockbin.io/)

## Getting Started

### Prerequisites

- Android Studio
- Java/Kotlin
- Android SDK

### Installation

1. Clone the repository:
    ```bash
    
    git clone https://github.com/siamsaleh/TaskGo.git
    
    ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.

### APK Download:
You can download the APK for direct installation:
[Download APK](https://github.com/siamsaleh/TaskGo/raw/refs/heads/master/TaskGo.apk)

## Demo:

### Video Demo:
[Watch the demo video](https://www.youtube.com/shorts/LRDV8Suqxsk)

## Commit Messages:
Descriptive and clear commit messages have been used for each change made in the project. Example:
**Types of commits:**
- `feat` : A new feature
- `fix` : A bug fix
- `docs` : Documentation only changes
- `style` : Code style changes (e.g., formatting, missing semi-colons)
- `refactor` : Code changes that neither fix a bug nor add a feature
- `test` : Adding or updating tests
- `chore` : Routine tasks (e.g., updating dependencies)

## Conclusion:
This task demonstrates my ability to handle Android development projects with an emphasis on UI design and API integration. Please feel free to contact me if you have any questions or need further clarification.

---
**Created by**: Saleh Ahmed Siam  
**GitHub**: (https://github.com/siamsaleh)
