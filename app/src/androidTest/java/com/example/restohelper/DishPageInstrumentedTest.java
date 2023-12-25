package com.example.restohelper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.restohelper.activities.DishPageActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DishPageInstrumentedTest {

    @Rule
    public ActivityTestRule<DishPageActivity> dishPageActivityActivityTestRule = new ActivityTestRule<>(DishPageActivity.class);

    @Test
    public void checkInfoDishPage(){
        onView(withId(R.id.dish_page_image_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.dish_page_name_text_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.dish_page_description_text_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.dish_page_price_text_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.dish_page_rub_text_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickPlusMinusButtonDishPage(){
        onView(withId(R.id.dish_page_plus_button))
                .perform(click());
        onView(withId(R.id.dish_page_plus_button))
                .perform(click());
        onView(withId(R.id.dish_page_minus_button))
                .perform(click());
        onView(withId(R.id.dish_page_quantity_text_view))
                .check(matches(withText("2")));
    }
}