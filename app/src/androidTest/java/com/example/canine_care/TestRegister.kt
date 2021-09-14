package com.example.canine_care

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.canine_care.ui.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class TestRegister {
    @get:Rule
    val testRule = ActivityScenarioRule(RegisterActivity::class.java)


    @Test
    fun RegistrationActivity() {
        Espresso.onView(withId(R.id.edtfname))
            .perform(ViewActions.typeText("Sumedh"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.edtlname))
            .perform(ViewActions.typeText("Dawadi"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.edtemail))
            .perform(ViewActions.typeText("sumedh@gmail.com"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.edtusername))
            .perform(ViewActions.typeText("sumedhda"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.edtpassword))
            .perform(ViewActions.typeText("sumedh123"))
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnRegistration))
            .perform(ViewActions.click())

        Thread.sleep(10000)

//        Espresso.onView(ViewMatchers.withId(R.id.tvSignup))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}