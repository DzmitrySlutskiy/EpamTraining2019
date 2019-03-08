package com.epam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.themes.androidcomponents.AndroidComponentsActivity;
import com.epam.themes.uicomponents.UIComponentsActivity;

public class LessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        findViewById(R.id.lesson_android_components_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(AndroidComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_ui_components_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLesson(UIComponentsActivity.class);
            }
        });
    }

    private void openLesson(Class<? extends Activity> lessonActivityClass) {
        startActivity(new Intent(this, lessonActivityClass));
    }
}
