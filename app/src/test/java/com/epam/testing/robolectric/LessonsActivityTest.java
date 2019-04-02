package com.epam.testing.robolectric;

import android.widget.TextView;

import com.epam.LessonsActivity;
import com.epam.cleancodetest.R;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class LessonsActivityTest {

    @Test
    public void testLessonsActivity_AvailableLessonsView() {
        final LessonsActivity activity = Robolectric.setupActivity(LessonsActivity.class);
        final TextView textView = activity.findViewById(R.id.available_lessons_title);

        Assert.assertNotNull(textView);
    }

    @Test
    public void testLessonsActivityLifecycle() {
        final ActivityController<LessonsActivity> controller = Robolectric.buildActivity(LessonsActivity.class);
        controller.create()
                .start()
                .resume()
                .visible()
                .get();

        controller.stop()
                .destroy();
    }
}