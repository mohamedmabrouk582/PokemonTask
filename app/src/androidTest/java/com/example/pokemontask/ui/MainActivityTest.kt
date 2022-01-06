package com.example.pokemontask.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @name Mohamed Mabrouk
 * Copyrights (c) 06/01/2022 created by Just clean
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity::class.java)
    }

}