package com.example.restohelper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.restohelper.activities.LoginActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void clickAndFillFieldsOpenCartLogin(){
        onView(withId(R.id.sign_in_email_edit_text))
                .perform(click(),
                        typeText("admin@example.com"));
        onView(withId(R.id.sign_in_password_edit_text))
                .perform(click(),
                        typeText("admin1"));
        onView(withId(R.id.sign_in_button))
                .perform(click());

        onView(isRoot()).perform(waitFor(5000));

        onView(withId(R.id.home_container))
                .check(matches(isDisplayed()));

        onView(withId(R.id.home_open_cart_activity_image_button)).
                perform(click());

        onView(isRoot()).perform(waitFor(3000));

        onView(withId(R.id.cart_total_price_text_view))
                .check(matches(isDisplayed()));
    }

    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }

            @Override public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }
}