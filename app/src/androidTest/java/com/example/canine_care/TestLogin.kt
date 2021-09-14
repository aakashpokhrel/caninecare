package com.example.canine_care

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.canine_care.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class TestLogin {
    @get:Rule
    val testRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun LoginActivityTest(){
        Espresso.onView(withId(R.id.etusername))
            .perform(ViewActions.typeText("saksham"))

        Espresso.onView(withId(R.id.etpassword))
            .perform(ViewActions.typeText("123456789"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(withId(R.id.btnLogin))
            .perform(ViewActions.click())

        Thread.sleep(2000)

        Espresso.onView(withId(R.id.immedical))
            .check(ViewAssertions.matches(ViewMatchers.withText("Profile")))

    }
}