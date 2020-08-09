# Baking Application

The Baking Application is built using the latest technology stack from Android Jetpack
as well as leveraging the power of third-party libraries to build an application that displays
cooking recipes, their ingredients, and video-guided instruction steps.

## Dependencies

The Baking Application uses the following dependencies:

* Room - A persistence library that helps create a local database cache of the application's data.
* LiveData - A life-cycle aware, data holder object that observes for data changes to update the UI.
* ViewModel - Stores and manages UI-related data in a life-cycle conscious way, allowing data to survive during 
 configuration changes like a screen rotation.
* Retrofit2 - A type-safe REST client making it easy to fetch & upload JSON (or any other structure data) via REST based web services.
* Retrofit2 RxJava Adapter - A type of Retrofit Call Adapter for extending support to RxJava service method return types.
* RxJava - Third party library allowing for event-based programming with the help of Observables and Subscribers.
* RxJava Android - Provides additional utility classes for the Android platform.
* Gson - Java based library used to help serialize / deserialize JSON objects.
* Gson Converter - Utility library that creates a Converter Factory for Retrofit to serialize / deserialize Gson objects.
* RecyclerView - A View displaying scrollable list of data sets, utilizing a combination of the ViewHolder & Adapter patterns to adjust for data changes.
* Picasso - Library used for manipulating and caching images.
* Material Design Components - Building blocks for designing UI's that provide a user rich experience.
* ExoPlayer - Application level media player features audio and video playback capabilities for Android.

## Installation

Android Studio 4.0 and Gradle version 4.0.1 are required to build and run the application.