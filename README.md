# F1 App
Application to get information about F1 drivers and teams performing in the 2022 season, as well as the GPs of the season.
Key features regarding its construction:
- Information reading from an API Rest as JSON objects, using Retrofit and GSON converter. API used: https://developer.sportradar.com/docs/read/racing/Formula_1_v2
- Kotlin coroutines
- Based on MVVM architecture. Use of ViewModel and LiveData to handle information coming from the API
- Use of Navigation Components with SafeArgs to navigate between fragments
- Use of ViewBinding and RecyclerView, with generic adapters
- Use of Kotlin scope functions
-Published on Google PlayStore - https://play.google.com/store/apps/details?id=com.F1App.f1app_v1.ui
