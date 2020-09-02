package com.project.twitter.model

import com.project.twitter.repository.model.detail.TweetDetail
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailModelTest {

    private val twitterHandle: String = "MehrozKh"
    private val tweetText: String =
        "Attending a useful training on attitude management.... https://t.co/8gCV0P5ads"
    private val tweetTimeStamp: String = "Wed Sep 02 07:08:36 +0000 2020"
    private val userAvatar: String =
        "http://pbs.twimg.com/profile_images/1197850061792780288/aH9uu77E_normal.jpg"
    private val tweetID: String = "1301054400182575104"

    @Mock
    lateinit var tweetDetail: TweetDetail


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(tweetDetail.tweetID).thenReturn(tweetID)
        Mockito.`when`(tweetDetail.twitterHandle).thenReturn(twitterHandle)
        Mockito.`when`(tweetDetail.tweetText).thenReturn(tweetText)
        Mockito.`when`(tweetDetail.tweetTimeStamp).thenReturn(tweetTimeStamp)
        Mockito.`when`(tweetDetail.userAvatar).thenReturn(userAvatar)
    }

    @Test
    fun testTweetID() {
        Assert.assertEquals("1301054400182575104", tweetDetail.tweetID)
    }

    @Test
    fun testTwitterHandle() {
        Assert.assertEquals("MehrozKh", tweetDetail.twitterHandle)
    }

    @Test
    fun testTweetText() {
        Assert.assertEquals(
            "Attending a useful training on attitude management.... https://t.co/8gCV0P5ads",
            tweetDetail.tweetText
        )
    }

    @Test
    fun testTweetTimeStamp() {
        Assert.assertEquals("Wed Sep 02 07:08:36 +0000 2020", tweetDetail.tweetTimeStamp)
    }

    @Test
    fun testUserAvatar() {
        Assert.assertEquals(
            "http://pbs.twimg.com/profile_images/1197850061792780288/aH9uu77E_normal.jpg",
            tweetDetail.userAvatar
        )
    }

}