package com.epam.themes.collectionviews;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.StudentsWebService;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.collectionviews.recyclerview.ItemTouchStudentsCallback;
import com.epam.themes.collectionviews.recyclerview.StudentsAdapter;
import com.epam.themes.util.ICallback;

import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    public static final int PAGE_SIZE = 10;
    public static final int MAX_VISIBLE_ITEMS = 40;

    private boolean mIsLoading = false;
    private StudentsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private final IWebService<Student> mWebService = new StudentsWebService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_students);

        final RecyclerView recyclerView = findViewById(R.id.activity_students_recycler_view);
        FloatingActionButton floatingActionButton = findViewById(R.id.activity_students_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudentDialog();
            }
        });

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
        new ItemTouchHelper(new ItemTouchStudentsCallback(mAdapter)).attachToRecyclerView(recyclerView);
        loadMoreItems(0, PAGE_SIZE);
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

    private void openStudentDialog() {
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_student, null);
        final EditText studentNameEditText = dialogView.findViewById(R.id.dialog_student_name);
        final EditText hwCountEditText = dialogView.findViewById(R.id.dialog_student_hw_count);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setView(dialogView)
                .setTitle("Student info")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogView.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);

                        Student student = new Student();
                        student.setName(studentNameEditText.getText().toString());
                        student.setHwCount(Integer.parseInt(hwCountEditText.getText().toString()));
                        addNewStudent(student);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        dialog.show();
    }

    private void addNewStudent(final Student newStudent) {
        mAdapter.addItem(0, newStudent);
        mWebService.addEntity(newStudent);
    }

}
