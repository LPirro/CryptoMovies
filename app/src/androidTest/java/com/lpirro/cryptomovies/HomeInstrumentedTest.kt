package com.lpirro.cryptomovies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lpirro.cryptomovies.presentation.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeInstrumentedTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun homeScreenDisplayedCorrectly() {
        onView(withId(R.id.topRatedMoviesSectionView)).check(matches(isDisplayed()))
        onView(withId(R.id.popularMoviesSectionView)).check(matches(isDisplayed()))
        onView(withId(R.id.nowPlayingMoviesSectionView)).check(matches(isDisplayed()))
        onView(withId(R.id.upcomingMoviesSectionView)).check(matches(isDisplayed()))
    }

    @Test
    fun clickOnTopRatedMovieWillShowDetailScreen() {

        Thread.sleep(10000)
        onView(
            allOf(
                withId(R.id.movieCoverRecyclerView),
                isDescendantOfA(withId(R.id.popularMoviesSectionView))
            )
        ).check(matches(isDisplayed()))
    }
}
