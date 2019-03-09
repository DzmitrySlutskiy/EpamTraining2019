package com.epam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.themes.androidcomponents.AndroidComponentsActivity;
import com.epam.themes.cleancode.CleanCodeActivity;
import com.epam.themes.uicomponents.UIComponentsActivity;

public class LessonsActivity extends AppCompatActivity {

    private ViewGroup firstLesson;
    private ViewGroup secondLesson;
    private ViewGroup thirdLesson;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        firstLesson = findViewById(R.id.fist_lesson);
        secondLesson = findViewById(R.id.second_lesson);
        thirdLesson = findViewById(R.id.third_lesson);

        setLessonParameters(firstLesson, R.drawable.ic_mentor_face, R.string.mentor_name_yahor,
                R.string.theme_clean_code, R.string.first_lesson_date);

        setLessonParameters(secondLesson, R.drawable.ic_account_circle, R.string.mentor_name_arziom,
                R.string.theme_android_components, R.string.second_lesson_date);

        setLessonParameters(thirdLesson, R.drawable.ic_mentor_face, R.string.mentor_name_yahor,
                R.string.theme_android_ui, R.string.third_lesson_date);

        findViewById(R.id.fist_lesson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(CleanCodeActivity.class);
            }
        });

        findViewById(R.id.second_lesson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(AndroidComponentsActivity.class);
            }
        });

        findViewById(R.id.third_lesson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

    }

    private void setLessonParameters(final ViewGroup lessonItem,
                                     final int icon,
                                     final int name,
                                     final int theme,
                                     final int date) {
        ((ImageView) lessonItem.findViewById(R.id.android_components_mentor_image_view))
                .setImageResource(icon);

        ((TextView) lessonItem.findViewById(R.id.android_components_mentor_text_view))
                .setText(name);

        ((TextView) lessonItem.findViewById(R.id.android_components_lesson_theme))
                .setText(theme);

        ((TextView) lessonItem.findViewById(R.id.android_components_lesson_date))
                .setText(date);
    }

    private void openLesson(final Class<? extends Activity> lessonActivityClass) {
        startActivity(new Intent(this, lessonActivityClass));
    }
}
