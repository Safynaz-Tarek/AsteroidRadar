# Application Summary:

The Asteroid Radar application is an Android app developed as part of the Udacity Android Developer Nanodegree program. The app allows users to view information about near-Earth asteroids, including their size, velocity, and distance from Earth.

The app fetches data about asteroids using the NASA API, which is then displayed in a user-friendly interface. Users can view a list of all upcoming asteroids, or view details about a specific asteroid. The app also includes a feature to save asteroids to a "favorites" list for easy access.

In addition to displaying asteroid information, the app also includes a feature to display a picture of the day from NASA's Astronomy Picture of the Day API. Users can also set a reminder to check for upcoming asteroids on a specific date.

The Asteroid Radar application demonstrates the use of several key Android development concepts, such as using APIs to fetch and display data, implementing RecyclerViews and Adapters, handling user input with forms, and creating notifications. The app also includes advanced features such as implementing a Room database to store favorite asteroids and using Glide to load images.

# Technical Features:

1. REST APIs: The app uses two REST APIs - the NASA API and the Astronomy Picture of the Day API - to fetch data about asteroids and display images. The app uses Retrofit library to make network requests and retrieve data in JSON format.

3. RecyclerView and Adapters: The app uses RecyclerViews to display lists of asteroids and images, and uses custom Adapters to populate the RecyclerViews with data. The app also implements a ClickListener interface to handle user clicks on the asteroid items.

5. Room Database: The app uses the Room library to create and manage a local database to store favorite asteroids. The app uses LiveData and ViewModel to observe changes in the database and updates the UI accordingly.

7. Notifications: The app uses Android's notification system to remind users to check for upcoming asteroids. The app uses JobScheduler to schedule periodic background tasks to check for upcoming asteroids and create notifications.

9. Glide: The app uses the Glide library to load images from the Astronomy Picture of the Day API. Glide provides a convenient way to load, cache and display images in the app, while handling image resizing and caching.

11. Material Design: The app uses Material Design principles to create a consistent and visually appealing user interface. The app includes features such as collapsing toolbar, floating action button, and card view to create a modern and intuitive interface.

Overall, the Asteroid Radar application demonstrates the use of several key Android development concepts and libraries, such as Retrofit, RecyclerView, Room, Notifications, Glide, and Material Design. These features help to create a robust and maintainable app that provides a great user experience.
