package com.example.healingwords.doc_profile.tests

import android.content.Context
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.healingwords.DocMainUI
import com.example.healingwords.UserViewDocProfile
import org.junit.*
import com.example.healingwords.R
import com.example.healingwords.ratingCalculation.RatingCalc
import com.example.healingwords.ratingCalculation.RatingsCalcAndSetTV
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import junit.framework.TestCase.assertEquals
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RatingTest {
    private lateinit var auth: FirebaseAuth
    private lateinit var uid:String
    private lateinit var docUid:String
    private var rating:String ? = null
    private lateinit var Activity : UserViewDocProfile
    private lateinit var appContext: Context

    @Test
    fun useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.healingwords", appContext.packageName)
    }

    @get:Rule
    val activityScenarioRuleUser = ActivityScenarioRule(UserViewDocProfile::class.java)


    private lateinit var activityScenarioUser: ActivityScenario<UserViewDocProfile>



    @Before
    fun setUp() {
        activityScenarioUser = activityScenarioRuleUser.scenario
        activityScenarioUser.onActivity { activity ->
            Activity = activity
        }

    }

    @Test
    fun getUid() {
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid!!
    }



    @Test
    fun testRatings() {
        getUid()
        val tv = Activity.findViewById<TextView>(R.id.tvTotalRatingDocProfileUserView)
        val rate = RatingsCalcAndSetTV()
        val finalRatingTV = rate.calculate("CK2dLXaVTBYzwMi1SrEW1KA2M8n1", tv )
        val value = "0.0/5"
        val tvText = finalRatingTV.text
        assertEquals(tvText, value)
    }


    @After
    fun tearDown() {
        activityScenarioUser.close()
    }

}