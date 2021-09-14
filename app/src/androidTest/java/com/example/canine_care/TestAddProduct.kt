package com.example.canine_care

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.canine_care.ui.AddProductActivity
import org.junit.Rule
import org.junit.Test

class TestAddProduct {
    @get:Rule
    val testRule = ActivityScenarioRule(AddProductActivity::class.java)

    @Test
    fun testAddProduct(){
        Espresso.onView(ViewMatchers.withId(R.id.etAddPname))
            .perform(ViewActions.typeText("Chicken"))
        Espresso.onView(ViewMatchers.withId(R.id.etAddDesc))
            .perform(ViewActions.typeText("Good"))
        Espresso.onView(ViewMatchers.withId(R.id.etAddPrice))
            .perform(ViewActions.typeText("2500"))


        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.btnSave))
            .perform(ViewActions.click())

    }
}