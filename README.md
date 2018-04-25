# RemindMeAt

## Introduction
The purpose of the app allows you to set an item, within this item you can set your location using google maps and details about that location. Using Geofencing, once at the set location Geofence will be triggered and a notification will display details about the location.


## Technologies
- SQLite
- Google Play Services (Maps)
- Google Play Services (Locations)
- Geofence

## How to use

Login Screen
![login](https://github.com/dvdprr6/RemindMeAt/blob/sqlite/images/login.png)

Register Screen
![register](https://github.com/dvdprr6/RemindMeAt/blob/sqlite/images/register.png)

Main Menu
![main-menu](https://github.com/dvdprr6/RemindMeAt/blob/sqlite/images/main-menu.png)

Map
![maps](https://github.com/dvdprr6/RemindMeAt/blob/sqlite/images/maps.png)

List
![list](https://github.com/dvdprr6/RemindMeAt/blob/sqlite/images/list.png)

Ceate Screen
![create](https://github.com/dvdprr6/RemindMeAt/blob/sqlite/images/create.png)

Update Screen
![update](https://github.com/dvdprr6/RemindMeAt/blob/sqlite/images/update.png)


## Testing
No unit tests are available in this application, all tests were done using system testing. To test the geofence, I would set a specific location on google maps and head to that location. Once at the location I would be notified.

## Future of App
- Update/remove location in geofence
- Android Room implementation (ORM)
- Summary of all locations (google maps button)
- Set complete, incomplete tag to item
- Bettern implemetation of login
