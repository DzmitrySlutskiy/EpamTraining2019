package com.epam;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.cleancodetest.databinding.ActivityLessonsBinding;
import com.epam.themes.LessonItemModel;
import com.epam.themes.androidcomponents.AndroidComponentsActivity;
import com.epam.themes.collectionviews.CollectionViewsActivity;
import com.epam.themes.compoundview.CompoundViewActivity;
import com.epam.themes.uicomponents.GooglePodcastsActivity;
import com.epam.themes.uicomponents.UIComponentsActivity;
import com.epam.themes.uicomponents.VKPageActivity;

public class LessonsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        bindLessonsItems();
        setOnClickListeners();
    }

    private void openLesson(Class<? extends Activity> lessonActivityClass) {
        startActivity(new Intent(this, lessonActivityClass));
    }

    private void bindLessonsItems() {
        ActivityLessonsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_lessons);
        binding.setAndroidComponentsLessonItemModel(new LessonItemModel(
                R.string.theme_android_components,
                R.string.android_components_lesson_date,
                R.string.android_components_mentor_name,
                R.drawable.ic_account_circle));

        binding.setAndroidUILessonItemModel(new LessonItemModel(
                R.string.theme_android_ui,
                R.string.android_ui_lesson_date,
                R.string.android_ui_mentor_name,
                R.drawable.ic_account_circle));

        binding.setCompoundViewLessonItemModel(new LessonItemModel(
                R.string.theme_android_compound_view,
                R.string.compound_view_lesson_date,
                R.string.compound_view_mentor_name,
                R.drawable.ic_account_circle));

        binding.setCollectionComponentsViewsItemModel(new LessonItemModel(
                R.string.theme_android_collection_views,
                R.string.android_collection_views_lesson_date,
                R.string.android_collection_views_mentor_name,
                R.drawable.ic_account_circle));

        binding.setVkPageItemModel(new LessonItemModel(
                R.string.theme_vk_page_view,
                R.string.vk_page_lesson_date,
                R.string.vk_page_mentor_name,
                R.drawable.ic_account_circle));
        binding.setGooglePodcastsItemModel(new LessonItemModel(
                R.string.theme_google_podcasts_view,
                R.string.vk_page_lesson_date,
                R.string.google_podcasts_mentor_name,
                R.drawable.ic_account_circle));
    }

    private void setOnClickListeners() {
        findViewById(R.id.lesson_components_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(AndroidComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_ui_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_compound_view_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(CompoundViewActivity.class);
            }
        });

        findViewById(R.id.lesson_collection_components_views_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(CollectionViewsActivity.class);
            }
        });

        findViewById(R.id.vk_page_view_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(VKPageActivity.class);
            }
        });

        findViewById(R.id.google_podcasts_view_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(GooglePodcastsActivity.class);
            }
        });
    }
}
