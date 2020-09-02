# TwitterMap
**Project Description**</br></br>
This project integrates Twitter API to fetch and display a set of tweets on a Google map to a user depending upon a specific radius. The radius is customizable by the user and will allow users
to see the detailed tweets having geo coordinates. User is able to click a specific tweet on a map to see its detail which is represented as a Google marker on the map.
User is able to favorite that tweet or retweet it if they prefer.
User is also able to search tweets using a keyword or hashtag. A list of tweets will appear on screen where user can view attached content to a tweet as well including videos or images.</br></br>

**Features**</br>
- Twitter Authentication
- Twitter Search with radius
- Twitter Search with keyword and hashtag
- Twitter favorite a tweet
- Twitter retweet a tweet
- Google Maps integration
- Continuous polling of recent tweets after every 20 seconds
- If the number of tweets becomes equal to 100 or greater, the most old 10 tweets will be removed from the Map. 

**Technologies**</br>
- Kotlin
- MVVM
- Dagger 2
- LiveData
- Rxjava
- Retrofit

**Unit Testing**</br>
- Mockito
- Junit
- MockWebServer

## How to use it</br>
Clone or download the zip file. After building and running the project you will see a startup screen where you will be asked Location and GPS related permissions.
You have to enable those permission for the location services to work. You will see a **Login with Twitter** button. You need to click the button and Twitter will redirect you to authentication page where you have to provide your twitter credentials to proceed.
This step is important as you will not be able to create any API calls for twitter if you are not authenticated. After authetication you will be redirected to Map screen where you will see your current location marker and may be some other markers representing the most recent tweets in a 5km radius from your location.
Ofcourse this value is customizable from the bottom of Map screen where you can enter any value in Kilometers.
You may click a marker and see the detail of a tweet. You will see a new screen if you tap on a marker with a pop up window saying **View Detail**. On the detail page you can see the tweet detail where you can like or retweet that particular tweet.
Furthermore you can also go and search some tweets based on **Keyword or Hashtag**. The content will have videos and images for the tweet as well.

