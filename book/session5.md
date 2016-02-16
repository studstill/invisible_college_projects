# Robot Map 1 (Robot Heart Session 5)

This 2 part section of the course (Robot Map) will give you practice in creating a simple
map-based app that uses your current location as input, displays geographical information
on a map, and can tag and save virtual objects with this location.

# Goals

- Get Mapbox SDK set up to display a map within your Android app
- Plot the user location on a map
- Understand how Android apps are location-aware and how location data is represented
- Understand what SDKs are and how to manage them within your app
- *Stretch goal:* Style your own Mapbox map and include it in your app.

# Table of Contents
1. [Basic setup of Mapbox SDK](#basic-setup-of-mapbox-sdk)
2. [Displaying user's location on map](#displaying-users-location-on-map)
3. [Location updates and battery usage](#location-updates-and-battery-usage)
4. [Styling your map](#styling-your-map)

### Basic setup of Mapbox SDK

We start with the default project structure generated when starting a new project with Android Studio (ensure minSdkVersion is 15).

Our first task is to get a hold of the Mapbox Android SDK and get it set up to display in our app. Setup instructions and SDK docs are found [here on the Mapbox website](https://www.mapbox.com/android-sdk/).

Following the instructions for setting up the [mapbox-sdk using Gradle](https://www.mapbox.com/android-sdk/#gradle), you can include the SDK as a dependency of the app. To do this simply add this code to the `app/build.gradle` file:

```
repositories {
    mavenCentral()
}

dependencies {
    compile ('com.mapbox.mapboxsdk:mapbox-android-sdk:2.2.0@aar'){
        transitive=true
    }
}
```

Next you must add the appropriate user permissions to the `AndroidManifest.xml` file (these permissions are required to allow grabbing the map data to display from a remote host):

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
```

To include a map in your app you first need to sign up for a Mapbox account at [mapbox.com](https://www.mapbox.com/studio/signup/?path=%2Fstudio%2Faccount%2Fapps) to get your access token which is required to use the Mapbox APIs used to display the map. 

Once you have an access token you can add the MapView XML to the activity layout in `res/layout/activity_mapbox_map.xml`:

```xml
<com.mapbox.mapboxsdk.views.MapView
android:id="@+id/mapview"
android:layout_width="fill_parent"
android:layout_height="fill_parent"
mapbox:access_token="<your access token here>"/>
```

Now you can programatically access the MapView to configure it in the onCreate method of the Activity by adding the following inside onCreate:

```java
MapView mapView = (MapView) findViewById(R.id.mapboxMapView);
mapView.setStyleUrl(Style.MAPBOX_STREETS);
mapView.setCenterCoordinate(new LatLng(47.605967, -122.334539));
mapView.setZoomLevel(11);
mapView.onCreate(savedInstanceState);
```

This initializes the map with the Mapbox Streets styling and sets the center of the map on Seattle, WA.


### Displaying user's location on map

In order to track a user's location we must add the following user permissions to `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

The simplest way to display the user's location on the map is to use the build-in Mapbox SDK function. Simply add this line of code to `MapboxMapActivity.java`'s onCreate method to track and display user location on the map:

```java
mapView.setMyLocationEnabled(true);
```

Then you can set Mapbox to either continuously track and update the user's location or disable tracking by calling `mapView.setMyLocationTrackingMode(..)`. This call does a number of things behind the scenes with the Android Location APIs. You should understand that there are multiple possible ways your phone can determine your location, which vary in terms of accuracy. Under the hood the Mapbox SDK makes use of the [LocationRequest](http://developer.android.com/reference/com/google/android/gms/location/LocationRequest.html) class to get user location updates. For more fine-grained control of location updates refer to [this documentation](http://developer.android.com/training/location/receive-location-updates.html).


### Location updates and battery usage

Requesting location updates can have a significant impact on the power consumption caused by your app. Basically, the more often location updates are requested the more battery your app will consume. Also different location provider have different impacts, and in general the more precise the provider the more power is consumed per update.

By default the Mapbox SDK will continuously request location updates which can make a significant impact on the power consumption of your app. You can disable location updates using the following code:

```java
mapView.setMyLocationTrackingMode(MyLocationTracking.TRACKING_NONE);
```

There are various ways to reduce the power consumption of requesting the user's location. One such method is to use the low-power mode when issuing a [LocationRequest](http://developer.android.com/training/location/receive-location-updates.html#location-request) to update the user's Location. 



### Styling your map

To style your own Mapbox map with Mapbox Studio and display your custom-styled map in your app. You can first create a new Style using the [Mapbox Studio](https://www.mapbox.com/mapbox-studio/) web application and then copy the style URL (looks something like `mapbox://styles/your_username/cihm7bjhv001b93kmi2mzoysi`), then input it into the setStyleUrl method call in onCreate of `MapboxMapActivity.java` like this: `mapView.setStyleUrl("mapbox://styles/your_username/cihm7bjhv001b93kmi2mzoysi");`
