package com.project.twitter.repo


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.twitter.CurrentThreadExecutor
import com.project.twitter.repository.api.ApiService
import com.project.twitter.repository.model.tweet.TwitterSearchResponse
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
class SearchTweetsInRadiusRepoTest {

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
    fun testSearchTweetsResponse() {
        val responseBody =
            """
           { "statuses": [
        {
            "created_at": "Tue Sep 01 19:11:06 +0000 2020",
            "id": 1300873838088122371,
            "id_str": "1300873838088122371",
            "text": "@DrChrisCombs Brilliant",
            "truncated": false,
            "entities": {
                "hashtags": [],
                "symbols": [],
                "user_mentions": [
                    {
                        "screen_name": "DrChrisCombs",
                        "name": "Chris Combs",
                        "id": 1039894373742333952,
                        "id_str": "1039894373742333952",
                        "indices": [
                            0,
                            13
                        ]
                    }
                ],
                "urls": []
            },
            "metadata": {
                "iso_language_code": "en",
                "result_type": "recent"
            },
            "source": "<a href=\"http://twitter.com/download/android\" rel=\"nofollow\">Twitter for Android</a>",
            "in_reply_to_status_id": 1300787794793713664,
            "in_reply_to_status_id_str": "1300787794793713664",
            "in_reply_to_user_id": 1039894373742333952,
            "in_reply_to_user_id_str": "1039894373742333952",
            "in_reply_to_screen_name": "DrChrisCombs",
            "user": {
                "id": 843281742,
                "id_str": "843281742",
                "name": "Farid ud Din",
                "screen_name": "farid_salah",
                "location": "Lahore, Model town ",
                "description": "Lahorite, music and Sports enthusiast, love to cook, builder by profession",
                "url": null,
                "entities": {
                    "description": {
                        "urls": []
                    }
                },
                "protected": false,
                "followers_count": 236,
                "friends_count": 1501,
                "listed_count": 3,
                "created_at": "Mon Sep 24 09:48:37 +0000 2012",
                "favourites_count": 6705,
                "utc_offset": null,
                "time_zone": null,
                "geo_enabled": true,
                "verified": false,
                "statuses_count": 3238,
                "lang": null,
                "contributors_enabled": false,
                "is_translator": false,
                "is_translation_enabled": false,
                "profile_background_color": "F5ABB5",
                "profile_background_image_url": "http://abs.twimg.com/images/themes/theme13/bg.gif",
                "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme13/bg.gif",
                "profile_background_tile": true,
                "profile_image_url": "http://pbs.twimg.com/profile_images/1154359205173846018/rKeTdcJK_normal.jpg",
                "profile_image_url_https": "https://pbs.twimg.com/profile_images/1154359205173846018/rKeTdcJK_normal.jpg",
                "profile_banner_url": "https://pbs.twimg.com/profile_banners/843281742/1355766218",
                "profile_link_color": "94D487",
                "profile_sidebar_border_color": "000000",
                "profile_sidebar_fill_color": "000000",
                "profile_text_color": "000000",
                "profile_use_background_image": true,
                "has_extended_profile": true,
                "default_profile": false,
                "default_profile_image": false,
                "following": false,
                "follow_request_sent": false,
                "notifications": false,
                "translator_type": "none"
            },
            "geo": null,
            "coordinates": null,
            "place": null,
            "contributors": null,
            "is_quote_status": false,
            "retweet_count": 0,
            "favorite_count": 0,
            "favorited": false,
            "retweeted": false,
            "lang": "en"
                    }],
    "search_metadata": {
        "completed_in": 0.256,
        "max_id": 1300921885132312578,
        "max_id_str": "1300921885132312578",
        "next_results": "?max_id=1300771358029344768&q=%22%22&geocode=31.4566663%2C74.3098917%2C5km&include_entities=1",
        "query": "%22%22",
        "refresh_url": "?since_id=1300921885132312578&q=%22%22&geocode=31.4566663%2C74.3098917%2C5km&include_entities=1",
        "count": 15,
        "since_id": 0,
        "since_id_str": "0"
    }}""".trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setHeader("Content-Type", "application/json; charset=UTF-8")
                .setResponseCode(200)
                .setBody(responseBody)
        )

        var error: Throwable? = null
        var twitterSearchResponse: TwitterSearchResponse? = null
        apiService.getTweetsInSpecificRadius("", "31.4566663,74.3098917,5km", "recent", 100)
            .enqueue(object : Callback<TwitterSearchResponse?> {
                override fun onResponse(
                    call: Call<TwitterSearchResponse?>,
                    response: Response<TwitterSearchResponse?>
                ) {
                    twitterSearchResponse = response.body()
                }

                override fun onFailure(call: Call<TwitterSearchResponse?>, t: Throwable) {
                    error = t
                }

            })


        val request = mockWebServer.takeRequest()
        assertThat(
            request.requestLine,
            equalTo("GET /1.1/search/tweets.json?q=&geocode=31.4566663%2C74.3098917%2C5km&result_type=recent&count=100 HTTP/1.1")
        );
        Assert.assertNull(error)
        Assert.assertNotNull(twitterSearchResponse)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}