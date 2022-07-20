
![Logo](https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/logo.png)


## Screenshots

<p float="left">
<img width="200" height="400" src="https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/screenshot_1.png">
<img width="200" height="400" src="https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/screenshot_2.png">
<img width="200" height="400" src="https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/screenshot_3.png">
</p>

![App Screenshot](https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/screenshot_1.png)

![App Screenshot](https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/screenshot_2.png)

![App Screenshot](https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/screenshot_3.png)
# Astro Photos

Astro Photos downloads and displays random space photos from NASA API.

Astro Photos is implemented using Android Clean Architecture, Retrofit2,
Dagger Hilt, ViewModel, Coroutines, Room, Navigation Components and some
other libraries from the [Android Jetpack](https://developer.android.com/jetpack). 


<a href='https://play.google.com/store/apps/details?id=com.uxstate'><img alt='Get it on Google Play' src='https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/google_play_store%20_badge.png' width="280"/></a>
## Architecture
The Architecture of this application relies and complies with the following points:

* Has 3 Screen destination which use Compose Destination to manage navigation
* Uses Model-View-ViewModel (MVVM) pattern with Clean Architecture (data, domain and presentation)

![Clean Architecture](https://github.com/Tonnie-Dev/AsteroidsInCompose/blob/master/media/clean_architecture_diagram.png)
## Tech Stack

## Technologies used:

* [Android KTX](https://developer.android.com/kotlin/ktx) - helps to write more concise, idiomatic Kotlin code.
* [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern toolkit for building native Android UI
* [Material Design 3](https://m3.material.io/) - an adaptable system of guidelines, components, and tools that support the best practices of user interface design.
* [Compose Destinations](https://github.com/raamcosta/compose-destinations) - used to handle all navigations and arguments passing while hiding the complex, non-type-safe and boilerplate code
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [Dagger Hilt](https://dagger.dev/hilt/) - used for Dependency Injection.
* [Coil](https://coil-kt.github.io/coil/) - an image loading library for Android backed by Kotlin Coroutines
* [Moshi](https://github.com/square/moshi) - Android JSON parser for converting JSON string into Kotlin objects. 
* [Retrofit](https://square.github.io/retrofit/) a REST Client for Android which makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.
* [Coroutines and Kotlin Flow](https://kotlinlang.org/docs/reference/coroutines-overview.html) - used to manage the local storage i.e. `writing to and reading from the database`. Coroutines help in managing background threads and reduces the need for callbacks.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Timber](https://github.com/JakeWharton/timber) - a logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [Lottie Animations](https://lottiefiles.com/) - provides Lightweight and scalable animations files
## Deployment

Instant Weather requires a minimum API level of 21. Clone the repository or download the code directly. 

You will need an API key i.e. `API_KEY` from [NASA Open APIs](https://api.nasa.gov/) to request data. 



Each record follows this structure:

````JSON
{
        "copyright": "Robert Gendler",
        "date": "2008-11-27",
        "explanation": "Large galaxies grow by eating small ones ..,
        "media_type": "image",
        "service_version": "v1",
        "title": "Galaxies in the River",
        "url": "https://apod.nasa.gov/apod/image/0811/NGC1532_gendler_c800.jpg"
    }
  
````
## API Reference

#### Get a list of Astro Photos

```http
  GET planetary/apod
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `String` | **Required**. Your API key |
| `count` | `Int` | **Required**. Your API key |

Returns a list of APODs (Astronomy Picture of the Day)


## Contributing

Contributions are always welcome!

## Contribution
All contributions are welcome. If you are interested in seeing a particular feature implemented in this app, please open a new issue after which you can make a PR!

![Alt](https://repobeats.axiom.co/api/embed/84dfd3cd94832805dbcaa3569ec855d19e5c9401.svg "Repobeats analytics image")


## License

MIT License

Copyright (c) [2022] [Tonnie Dev]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

