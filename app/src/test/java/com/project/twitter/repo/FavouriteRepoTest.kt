package com.project.twitter.repo


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.twitter.CurrentThreadExecutor
import com.project.twitter.repository.api.ApiService
import com.project.twitter.repository.model.favourite.FavouriteResponse
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.equalTo
import org.junit.*
import org.junit.Assert.assertThat
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


@RunWith(JUnit4::class)
class FavouriteRepoTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockWebServer = MockWebServer()

    lateinit var apiService: ApiService


    @Before
    fun setup() {
        val currentThreadExecutor = CurrentThreadExecutor()
        val dispatcher = Dispatcher(currentThreadExecutor)
        val okHttpClient = OkHttpClient.Builder().dispatcher(dispatcher).build()
        MockitoAnnotations.initMocks(this)
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .callbackExecutor(currentThreadExecutor)
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }


    @Test
    fun testFavouriteTweetResponse() {
        val responseBody =
            """
           { 
           "created_at": "Wed Sep 02 07:08:36 +0000 2020",
    "id": 1301054400182575104,
    "id_str": "1301054400182575104",
    "text": "Attending a useful training on attitude management.... https://t.co/8gCV0P5ads",
    "truncated": false,
    "entities": {
        "hashtags": [],
        "symbols": [],
        "user_mentions": [],
        "urls": [],
        "media": [
            {
                "id": 1301054391957565441,
                "id_str": "1301054391957565441",
                "indices": [
                    55,
                    78
                ],
                "media_url": "http://pbs.twimg.com/media/Eg5GE4IXsAEdmAb.jpg",
                "media_url_https": "https://pbs.twimg.com/media/Eg5GE4IXsAEdmAb.jpg",
                "url": "https://t.co/8gCV0P5ads",
                "display_url": "pic.twitter.com/8gCV0P5ads",
                "expanded_url": "https://twitter.com/technoverx/status/1301054400182575104/photo/1",
                "type": "photo",
                "sizes": {
                    "thumb": {
                        "w": 150,
                        "h": 150,
                        "resize": "crop"
                    },
                    "small": {
                        "w": 510,
                        "h": 680,
                        "resize": "fit"
                    },
                    "large": {
                        "w": 768,
                        "h": 1024,
                        "resize": "fit"
                    },
                    "medium": {
                        "w": 768,
                        "h": 1024,
                        "resize": "fit"
                    }
                }
            }
        ]
    },
    "extended_entities": {
        "media": [
            {
                "id": 1301054391957565441,
                "id_str": "1301054391957565441",
                "indices": [
                    55,
                    78
                ],
                "media_url": "http://pbs.twimg.com/media/Eg5GE4IXsAEdmAb.jpg",
                "media_url_https": "https://pbs.twimg.com/media/Eg5GE4IXsAEdmAb.jpg",
                "url": "https://t.co/8gCV0P5ads",
                "display_url": "pic.twitter.com/8gCV0P5ads",
                "expanded_url": "https://twitter.com/technoverx/status/1301054400182575104/photo/1",
                "type": "photo",
                "sizes": {
                    "thumb": {
                        "w": 150,
                        "h": 150,
                        "resize": "crop"
                    },
                    "small": {
                        "w": 510,
                        "h": 680,
                        "resize": "fit"
                    },
                    "large": {
                        "w": 768,
                        "h": 1024,
                        "resize": "fit"
                    },
                    "medium": {
                        "w": 768,
                        "h": 1024,
                        "resize": "fit"
                    }
                }
            }
        ]
    },
    "source": "<a href=\"http://twitter.com/download/android\" rel=\"nofollow\">Twitter for Android</a>",
    "in_reply_to_status_id": null,
    "in_reply_to_status_id_str": null,
    "in_reply_to_user_id": null,
    "in_reply_to_user_id_str": null,
    "in_reply_to_screen_name": null,
    "user": {
        "id": 217634399,
        "id_str": "217634399",
        "name": "nabeel khalid",
        "screen_name": "technoverx",
        "location": "Lahore, Pakistan",
        "description": "Assistant Professor, Faculty of Engineering, UCP.\nPatron Society for Electronics and Telecommunications",
        "url": null,
        "entities": {
            "description": {
                "urls": []
            }
        },
        "protected": false,
        "followers_count": 57,
        "friends_count": 253,
        "listed_count": 0,
        "created_at": "Sat Nov 20 02:12:29 +0000 2010",
        "favourites_count": 712,
        "utc_offset": null,
        "time_zone": null,
        "geo_enabled": true,
        "verified": false,
        "statuses_count": 1606,
        "lang": null,
        "contributors_enabled": false,
        "is_translator": false,
        "is_translation_enabled": false,
        "profile_background_color": "C0DEED",
        "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
        "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
        "profile_background_tile": false,
        "profile_image_url": "http://pbs.twimg.com/profile_images/1197850061792780288/aH9uu77E_normal.jpg",
        "profile_image_url_https": "https://pbs.twimg.com/profile_images/1197850061792780288/aH9uu77E_normal.jpg",
        "profile_link_color": "1DA1F2",
        "profile_sidebar_border_color": "C0DEED",
        "profile_sidebar_fill_color": "DDEEF6",
        "profile_text_color": "333333",
        "profile_use_background_image": true,
        "has_extended_profile": true,
        "default_profile": true,
        "default_profile_image": false,
        "following": false,
        "follow_request_sent": false,
        "notifications": false,
        "translator_type": "none"
    },
    "geo": null,
    "coordinates": null,
    "place": {
        "id": "07d9f37289886001",
        "url": "https://api.twitter.com/1.1/geo/id/07d9f37289886001.json",
        "place_type": "poi",
        "name": "University of Central Punjab",
        "full_name": "University of Central Punjab",
        "country_code": "PK",
        "country": "Pakistan",
        "contained_within": [],
        "bounding_box": {
            "type": "Polygon",
            "coordinates": [
                [
                    [
                        74.26805534362794,
                        31.44694165778066
                    ],
                    [
                        74.26805534362794,
                        31.44694165778066
                    ],
                    [
                        74.26805534362794,
                        31.44694165778066
                    ],
                    [
                        74.26805534362794,
                        31.44694165778066
                    ]
                ]
            ]
        },
        "attributes": {}
    },
    "contributors": null,
    "is_quote_status": false,
    "retweet_count": 0,
    "favorite_count": 1,
    "favorited": true,
    "retweeted": false,
    "possibly_sensitive": false,
    "lang": "en"
           
           }""".trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setHeader("Content-Type", "application/json; charset=UTF-8")
                .setResponseCode(200)
                .setBody(responseBody)
        )

        var error: Throwable? = null
        var favouriteResponse: FavouriteResponse? = null
        apiService.getFavouriteResponse("1301054400182575104")
            .enqueue(object : Callback<FavouriteResponse?> {
                override fun onResponse(
                    call: Call<FavouriteResponse?>,
                    response: Response<FavouriteResponse?>
                ) {
                    favouriteResponse = response.body()
                }

                override fun onFailure(call: Call<FavouriteResponse?>, t: Throwable) {
                    error = t
                }

            })


        val request = mockWebServer.takeRequest()
        assertThat(
            request.requestLine,
            equalTo("POST /1.1/favorites/create.json?id=1301054400182575104 HTTP/1.1")
        );
        Assert.assertNull(error)
        Assert.assertEquals(favouriteResponse?.idStr, "1301054400182575104")
        Assert.assertNotNull(favouriteResponse)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}