package com.example.restohelper;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.restohelper.activities.LoginActivity;
import com.example.restohelper.activities.RegistrationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RegistrationInstrumentedTest {

    @Rule
    public ActivityTestRule<RegistrationActivity> registrationActivityActivityTestRule = new ActivityTestRule<>(RegistrationActivity.class);

    @Test
    public void clickAndFillFieldsRegistration(){
        onView(withId(R.id.sign_up_name_edit_text))
                .perform(click(),
                        typeText("admin"));
        onView(withId(R.id.sign_up_email_edit_text))
                .perform(click(),
                        typeText("admin@example.com"));
        onView(withId(R.id.sign_up_password_edit_text))
                .perform(click(),
                        typeText("admin1"));
        closeSoftKeyboard();
        onView(withId(R.id.sign_up_sign_in_text_view))
                .perform(click());
        onView(withId(R.id.sign_in_sign_in_text_view))
                .perform(click());

    }
}