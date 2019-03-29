package com.epam.themes.collectionviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.StudentsWebService;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.collectionviews.recyclerview.StudentEditFragment;
import com.epam.themes.collectionviews.recyclerview.StudentsAdapter;
import com.epam.themes.util.ICallback;

import java.util.List;

public class StudentsActivity extends AppCompatActivity {
    public static final int PAGE_SIZE = 10;
    public static final int MAX_VISIBLE_ITEMS = 40;

    private boolean isLoading = false;
    private StudentsAdapter studentsAdapter;
    private LinearLayoutManager layoutManager;
    private final IWebService<Student> webService = new StudentsWebService();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Student student = studentsAdapter.getSelectedStudent();

        if (id == R.id.edit_item && student != null) {
            StudentEditFragment dialog = StudentEditFragment.newInstance(student);
            dialog.show(getSupportFragmentManager(),getResources().getString(R.string.edit_student));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.students_recycler, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_students);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(android.R.id.list);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        studentsAdapter = new StudentsAdapter(this);
        recyclerView.setAdapter(studentsAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();

                if (totalItemCount > MAX_VISIBLE_ITEMS) {
                    studentsAdapter.setShowLastViewAsLoading(false);

                    return;
                }

                int visibleItemCount = layoutManager.getChildCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                        loadMoreItems(totalItemCount, totalItemCount + PAGE_SIZE);
                    }
                }
            }
        });

        new ItemTouchHelper(new TouchCallback(studentsAdapter)).attachToRecyclerView(recyclerView);
        loadMoreItems(0, PAGE_SIZE);
    }

    private void loadMoreItems(final int pStartPosition, final int pEndPosition) {
        isLoading = true;
        studentsAdapter.setShowLastViewAsLoading(true);
        webService.getEntities(pStartPosition, pEndPosition, new ICallback<List<Student>>() {

            @Override
            public void onResult(List<Student> pResult) {
                studentsAdapter.addItems(pResult);
                isLoading = false;
            }
        });
    }

    public void updateStudent(Student student) {
        webService.updateEntityById(student.getId(), student);
        studentsAdapter.updateStudent(student);
    }

    class TouchCallback extends ItemTouchHelper.SimpleCallback {
        private final StudentsAdapter adapter;

        public TouchCallback(final StudentsAdapter adapter) {
            super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END);
            this.adapter = adapter;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
            adapter.onItemMove(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());

            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            int adapterPosition = viewHolder.getAdapterPosition();

            if (RecyclerView.NO_POSITION != adapterPosition) {
                webService.removeEntity(adapter.getItemId(adapterPosition));
                adapter.onItemDismiss(adapterPosition);
            }
        }
    }

}
