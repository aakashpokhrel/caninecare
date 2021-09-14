package com.example.canine_care

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.canine_care.ui.AddProductActivity
import org.junit.Rule
import org.junit.Test

class TestAddPet {
    @get:Rule
    val testRule = ActivityScenarioRule(AddProductActivity::class.java)

    @Test
    fun testAddProduct(){
        Espresso.onView(ViewMatchers.withId(R.id.etAddPetName))
            .perform(ViewActions.typeText("Bull dog"))
        Espresso.onView(ViewMatchers.withId(R.id.etAddPetAge))
            .perform(ViewActions.typeText("5"))
        Espresso.onView(ViewMatchers.withId(R.id.etAddPetPiece))
            .perform(ViewActions.typeText("3"))
        Espresso.onView(ViewMatchers.withId(R.id.etAddPetDesc))
            .perform(ViewActions.typeText("Good"))
        Espresso.onView(ViewMatchers.withId(R.id.etAddPrice))
            .perform(ViewActions.typeText("40000"))


        Espresso.closeSoftKeyboard()
        Espresso.onView(ViewMatchers.withId(R.id.btnPetSave))
            .perform(ViewActions.click())

    }
}