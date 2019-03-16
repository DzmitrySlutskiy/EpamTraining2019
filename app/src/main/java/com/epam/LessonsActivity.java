package com.epam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.themes.collectionviews.CollectionViewsActivity;
import com.epam.themes.compoundview.CompoundViewActivity;
import com.epam.themes.uicomponents.UIComponentsActivity;

public class LessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        findViewById(R.id.lesson_components_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(AndroidComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_ui_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });


        findViewById(R.id.lesson_compound_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(CompoundViewActivity.class);
            }
        });

        findViewById(R.id.collectionComponentsView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(CollectionViewsActivity.class);
            }
        });
    }

    private void openLesson(Class<? extends Activity> lessonActivityClass) {
        startActivity(new Intent(this, lessonActivityClass));
    }
}