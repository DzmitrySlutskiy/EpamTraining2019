package com.epam.themes.collectionviews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.StudentsWebService;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.collectionviews.recyclerview.StudentsAdapter;
import com.epam.themes.collectionviews.recyclerview.StudentTouchCallback;
import com.epam.themes.util.ICallback;
import com.epam.themes.util.StudentAdapterCallback;

import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    public static final int PAGE_SIZE = 5;
    public static final int MAX_VISIBLE_ITEMS = 100;
    private final IWebService<Student> studentsWebService = new StudentsWebService();
    private boolean isLoadComplete = false;
    private boolean isLoading = false;
    private StudentsAdapter studentsAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_students);

        setupRecyclerView();

        loadMoreItems(0, PAGE_SIZE);
    }

    private void setupRecyclerView() {
        final RecyclerView studentsRecyclerView = findViewById(android.R.id.list);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        studentsRecyclerView.setLayoutManager(linearLayoutManager);
        studentsAdapter = new StudentsAdapter(this, new StudentAdapterCallback() {
            @Override
            public void onStudentChange(Student student) {
                studentsWebService.setHomework(student.getId(), student.getHwCount());
            }

            @Override
            public void onStudentRemove(long id) {
                studentsWebService.removeEntity(id);
                checkNeedAndLoadMore();
            }
        });

        studentsRecyclerView.setAdapter(studentsAdapter);
        studentsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                checkNeedAndLoadMore();
            }
        });

        new ItemTouchHelper(new StudentTouchCallback(studentsAdapter)).attachToRecyclerView(studentsRecyclerView);
    }

    private void checkNeedAndLoadMore() {
        int totalItemCount = linearLayoutManager.getItemCount() - 1;

        if (totalItemCount >= MAX_VISIBLE_ITEMS || isLoadComplete) {
            studentsAdapter.setShowLastViewAsLoading(false);
            return;
        }

        int visibleItemCount = linearLayoutManager.getChildCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        if (!isLoading) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems(totalItemCount, totalItemCount + PAGE_SIZE);
            }
        }
    }

    private void loadMoreItems(final int startPosition, final int endPosition) {
        isLoading = true;
        studentsAdapter.setShowLastViewAsLoading(true);
        studentsWebService.getEntities(startPosition, endPosition, new ICallback<List<Student>>() {
            @Override
            public void onResult(List<Student> resultList) {
                if (resultList != null) {
                    if (resultList.size() < PAGE_SIZE) {
                        isLoadComplete = true;
                    }

                    studentsAdapter.addItems(resultList);
                } else {
                    isLoadComplete = true;
                }

                isLoading = false;
            }
        });
    }
}
