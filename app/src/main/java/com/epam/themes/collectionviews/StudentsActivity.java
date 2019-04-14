package com.epam.themes.collectionviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.StudentsWebService;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.collectionviews.recyclerview.ItemTouchCallbackStudent;
import com.epam.themes.collectionviews.recyclerview.StudentsAdapter;
import com.epam.themes.util.ICallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StudentsActivity extends AppCompatActivity {

    public static final int PAGE_SIZE = 10;
    public static final int MAX_VISIBLE_ITEMS = 40;

    private List<String> studentsList = new ArrayList<>();
    private boolean mIsLoading = false;
    private StudentsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private final IWebService<Student> mWebService = new StudentsWebService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_students);

        studentsList = Arrays.asList(getResources().getStringArray(R.array.students));

        final RecyclerView recyclerView = findViewById(android.R.id.list);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new StudentsAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mLayoutManager.getItemCount();

                if (totalItemCount > MAX_VISIBLE_ITEMS) {
                    mAdapter.setShowLastViewAsLoading(false);

                    return;
                }

                int visibleItemCount = mLayoutManager.getChildCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                if (!mIsLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        loadMoreItems(totalItemCount, totalItemCount + PAGE_SIZE);
                    }
                }
            }
        });

        loadMoreItems(0, PAGE_SIZE);

        new ItemTouchHelper(new ItemTouchCallbackStudent(recyclerView, mAdapter))
                .attachToRecyclerView(recyclerView);

        findViewById(R.id.add_student_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addItem(addStudent());
            }
        });
    }

    private Student addStudent() {
        Random random = new Random();
        Student student = new Student();
        student.setName(studentsList.get(random.nextInt(studentsList.size())));
        student.setHwCount(1 + random.nextInt(10));
        return student;
    }

    private void loadMoreItems(final int pStartPosition, final int pEndPosition) {
        mIsLoading = true;
        mAdapter.setShowLastViewAsLoading(true);
        mWebService.getEntities(pStartPosition, pEndPosition, new ICallback<List<Student>>() {

            @Override
            public void onResult(List<Student> pResult) {
                mAdapter.addItems(pResult);
                mIsLoading = false;
            }
        });
    }
}
