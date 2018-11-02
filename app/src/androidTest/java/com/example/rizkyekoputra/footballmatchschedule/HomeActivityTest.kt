package com.example.rizkyekoputra.footballmatchschedule

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.rizkyekoputra.footballmatchschedule.IdlinkResource.EspressoIdlingResource
import com.example.rizkyekoputra.footballmatchschedule.R.id.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testTabAndRecyclerViewBehaviour() {
        onView(ViewMatchers.withId(tabs_main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        val tabView = onView(
                Matchers.allOf(ViewMatchers.withContentDescription("NEXT MATCH"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(tabs_main),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()))
        tabView.perform(ViewActions.click())

        onView(ViewMatchers.withId(list_event_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(list_event_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(ViewMatchers.withId(list_event_next)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, ViewActions.click()))
    }

    @Test
    fun addAndRemoveFavoriteBehaviour() {
        onView(ViewMatchers.withId(tabs_main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        val tabView = onView(
                Matchers.allOf(ViewMatchers.withContentDescription("NEXT MATCH"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(tabs_main),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()))
        tabView.perform(ViewActions.click())

        onView(ViewMatchers.withId(spinner_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(spinner_next)).perform(ViewActions.click())

        onView(ViewMatchers.withText("UEFA Champions League")).perform(ViewActions.click())
        onView(ViewMatchers.withId(list_event_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(list_event_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(ViewMatchers.withId(list_event_next)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, ViewActions.click()))

        onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Added to favorite")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        onView(ViewMatchers.withId(bottom_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(favorites)).perform(ViewActions.click())
        onView(ViewMatchers.withId(bottom_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(matches)).perform(ViewActions.click())
        tabView.perform(ViewActions.click())
        onView(ViewMatchers.withId(spinner_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(spinner_next)).perform(ViewActions.click())
        onView(ViewMatchers.withText("UEFA Champions League")).perform(ViewActions.click())
        onView(ViewMatchers.withId(list_event_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(list_event_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(ViewMatchers.withId(list_event_next)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, ViewActions.click()))

        onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Removed to favorite")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()

        onView(ViewMatchers.withId(bottom_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(favorites)).perform(ViewActions.click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}