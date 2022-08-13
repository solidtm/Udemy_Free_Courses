# Udemy_Free_Courses
Android mobile application which aids and allows easy and fast access to free courses, where a prospective student or learner can readily access free course, manage interests, review and see ratings and detais about courses and enroll in them.

Setup
This project follows the Clean Architecture, is based on the MVVM model and is organized according to the following (feature based) packages:

core
This contains functional logic for results returned from API and DB calls, base classes and custom views used accross the entire application

data layer
api
This contains logic concerning interaction to the server, including data classes (for holding serialized objects such as json) used for the client-server communication.

db
This contains any logic relating to creating, reading, writing, updating, and securing the database.

domain layer
This is a contract between the data layer and the presentation layer and contains all the use cases of the application and the repository

repository
The repository servers as a single source of truth for accessing data resources. All app related data is exposed to the UI through the repository.

presentation
This is where any logic related to the user interface is placed including android framework related classes eg. Activities, Fragments, Adapters, View Models etc

dependecy injection
This is holds any logic or definition for dependency injection.

Stack
Retrofit, Room, Hilt, Coroutines Flow|Channel, Android Jetpack


# How To Run:  

- Have Android studio installed
- Download zipped file here: 
- Unzip the project 
- Open project in Android studio
- Run the project on an emulator on android studio or on real device connected via usb cable to your computer.

# Or:  
- Download and Install attached apk file on and android device and launch.
