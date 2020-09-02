package com.project.twitter.model

import com.project.twitter.repository.model.search.SearchData
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchModelTest {

    private val mediaType: String = "video"
    private val twitterHandle: String = "MehrozKh"
    private val tweetText: String =
        "Attending a useful training on attitude management.... https://t.co/8gCV0P5ads"
    private val tweetTimeStamp: String = "Wed Sep 02 07:08:36 +0000 2020"
    private val userAvatar: String =
        "http://pbs.twimg.com/profile_images/1197850061792780288/aH9uu77E_normal.jpg"


    @Mock
    lateinit var searchData: SearchData


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(searchData.mediaType).thenReturn(mediaType)
        Mockito.`when`(searchData.twitterHandle).thenReturn(twitterHandle)
        Mockito.`when`(searchData.tweetText).thenReturn(tweetText)
        Mockito.`when`(searchData.tweetTimeStamp).thenReturn(tweetTimeStamp)
        Mockito.`when`(searchData.userAvatar).thenReturn(userAvatar)
    }

    @Test
    fun testMediaType() {
        Assert.assertEquals("video", searchData.mediaType)
    }

    @Test
    fun testTwitterHandle() {
        Assert.assertEquals("MehrozKh", searchData.twitterHandle)
    }

    @Test
    fun testTweetText() {
        Assert.assertEquals(
            "Attending a useful training on attitude management.... https://t.co/8gCV0P5ads",
            searchData.tweetText
        )
    }

    @Test
    fun testTweetTimeStamp() {
        Assert.assertEquals("Wed Sep 02 07:08:36 +0000 2020", searchData.tweetTimeStamp)
    }

    @Test
    fun testUserAvatar() {
        Assert.assertEquals(
            "http://pbs.twimg.com/profile_images/1197850061792780288/aH9uu77E_normal.jpg",
            searchData.userAvatar
        )
    }

}