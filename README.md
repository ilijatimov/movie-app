This is an app that fetches movies from api.themoviedb, and they are used for multiple purposes:

- showing a popular list of movies
- storing favourite ones in a local database (using room)
- showing all the details available for a movie on a new screen
- filtering movie list depending on user search input

  Setup instructions:

  1. Open Android Studio and clone the project (File -> New -> Project from Version Control), use the following url (https://github.com/ilijatimov/movie-app) 
  2. Wait for dependencies to get downloaded
  3. When the Gradle build is successful, run the app on an emulator (or real Android device) on the green play button in the top right of Android Studio

1. Architecture - app files are separated in multiple subfolders, depending on their use:
   1.1 data subfolder - models that are used for mapping the api responses , and the definitions of the queries made towards the local database (room)
   1.2 di subfolder - definition of all dependency injections used in the app (using hilt)
   1.3 networking subfolder - definition of all api calls made in the app (using retrofit)
   1.4 ui subfolder - consists of multiple subfolders, files are grouped by screen ( the screen's viewmodel and compose functions used to represent that screen)




