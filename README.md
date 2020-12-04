# OpenWeatherApp

<p align="center">
  <img src="https://github.com/dariobrux/OpenWeatherApp/blob/main/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" />
</p>

This Android application written in Kotlin permits you to retrieve the weather info about your favorite city from the https://openweathermap.org/ api.

|            |.              |        |
|:----------:|:-------------:|:------:|
| ![Splash screen](https://github.com/dariobrux/OpenWeatherApp/blob/main/other/screenshot/Screenshot_1607100380.png) |  ![Main screen - no data and ready to fill](https://github.com/dariobrux/OpenWeatherApp/blob/main/other/screenshot/Screenshot_1607100451.png) | ![Main screen - no data and no results found](https://github.com/dariobrux/OpenWeatherApp/blob/main/other/screenshot/Screenshot_1607100468.png) |
| ![Main screen - results found](https://github.com/dariobrux/OpenWeatherApp/blob/main/other/screenshot/Screenshot_1607100482.png) |    ![Main screen - results found - scrolled](https://github.com/dariobrux/OpenWeatherApp/blob/main/other/screenshot/Screenshot_1607101237.png)  |   ![Info screen](https://github.com/dariobrux/OpenWeatherApp/blob/main/other/screenshot/Screenshot_1607100486.png)  |

## In this app you will find:
- a splash screen
- a screen where you can type the location and get the weather results for the next 5 days
- an info screen, reachable by tap on a weather item for the previous point.

## The code contains:
- Only one Activity. It's a Single-Activity project.
- MVVM + Repository pattern as main architecture.
- Hilt as Dependency Injection
- Room Database
- Retrofit as HTTP Client
- JetPack Navigation Component to navigate between fragments
- Glide to show images
- Kotlin Coroutines for asynchronous code
- LiveData as observable data
- LeakCanary to check memory leaks
- Timber to log

There's also some Unit Tests and Espresso Tests.
