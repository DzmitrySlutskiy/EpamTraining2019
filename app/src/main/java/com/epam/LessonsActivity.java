package com.epam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.themes.uicomponents.UIComponentsActivity;

public class LessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        findViewById(R.id.lesson_components_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_github_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_clean_code_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_java_basics_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_android_studio_tools_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_view_components_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });



        //todo open ui activity
    }

    private void openLesson(Class<? extends Activity> lessonActivityClass) {
        startActivity(new Intent(this, lessonActivityClass));
    }
}
