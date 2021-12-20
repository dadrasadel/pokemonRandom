package com.globallogic.pokemon.ui.main

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.bilerplatemvvm.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher


@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class MainActivityTest{
    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @Test
    fun mainActivityIsLoaded() {
//        then
        onView(withId(R.id.root_layout)).check(matches(isDisplayed()))

    }
    @Test
    fun changeTextWhenClickButton() {
        //given
        onView(withId(R.id.btnGenerate)).perform(click())
        //then
        onView(withId(R.id.txtName)).check(matches(not(withText("adel"))))
    }
}