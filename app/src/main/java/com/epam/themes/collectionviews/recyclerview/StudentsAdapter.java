package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.uicomponents.LessonView;
import com.epam.themes.uicomponents.base.BaseViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean mIsShowLastViewAsLoading = false;

    private final LayoutInflater mInflater;
    private final List<Student> mStudents = new ArrayList<>();

    public StudentsAdapter(final Context pContext) {
        mInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup pParent,
                                                      @ViewType final int pViewType) {
        if (pViewType == ViewType.STUDENT) {
            return new BaseViewHolder<>(new LessonView(pParent.getContext()));
        } else {
            return new BaseViewHolder<>(mInflater.inflate(R.layout.layout_progress, pParent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder pViewHolder, final int pPosition) {
        if (getItemViewType(pPosition) == ViewType.STUDENT) {
            final Student student = mStudents.get(pPosition);

            ((LessonView) pViewHolder.itemView)
                    .setLessonDate(student.getName())
                    .setLessonTheme(String.valueOf(student.getHwCount()));
        }
    }

    @ViewType
    @Override
    public int getItemViewType(final int pPosition) {
        if (pPosition < mStudents.size()) {
            return ViewType.STUDENT;
        } else {
            return ViewType.LOADING;
        }
    }


    @Override
    public int getItemCount() {
        if (mIsShowLastViewAsLoading) {
            return mStudents.size() + 1;
        } else {
            return mStudents.size();
        }
    }

    public void setShowLastViewAsLoading(final boolean pIsShow) {
        if (pIsShow != mIsShowLastViewAsLoading) {
            mIsShowLastViewAsLoading = pIsShow;
            notifyDataSetChanged();
        }
    }

    public void addItems(final List<Student> pResult) {
        mStudents.addAll(pResult);
        notifyDataSetChanged();
    }

    @IntDef({ViewType.STUDENT, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {

        int STUDENT = 0;
        int LOADING = 1;
    }
}
