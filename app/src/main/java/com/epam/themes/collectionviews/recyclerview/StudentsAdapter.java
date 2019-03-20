package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.uicomponents.base.BaseViewHolder;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean isShowLastViewAsLoading = false;
    private int selectPosition = RecyclerView.NO_POSITION;
    private final LayoutInflater inflater;
    private final List<Student> students = new ArrayList<>();

    public StudentsAdapter(final Context pContext) {
        inflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup pParent, final int pViewType) {
        if (pViewType == ViewType.STUDENT) {
            StudentItemView itemView = new StudentItemView(pParent.getContext());
            final StudentItemViewHolder viewHolder = new StudentItemViewHolder(itemView);

            return viewHolder;
        } else {
            return new BaseViewHolder<>(inflater.inflate(R.layout.layout_progress, pParent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == ViewType.STUDENT) {
            final Student student = students.get(position);
            ((StudentItemView) viewHolder.itemView).setName(student.getName()).setEmail(student.getEmail())
                    .setAddress(student.getAddress() );
            viewHolder.itemView.setActivated(selectPosition == position);
            viewHolder.itemView.setBackgroundColor(selectPosition == position ? Color.GRAY : Color.TRANSPARENT);
        }
    }

    @ViewType
    @Override
    public int getItemViewType(final int pPosition) {
        if (pPosition < students.size()) {
            return ViewType.STUDENT;
        } else {
            return ViewType.LOADING;
        }
    }


    @Override
    public int getItemCount() {
        if (isShowLastViewAsLoading) {
            return students.size() + 1;
        } else {
            return students.size();
        }
    }

    public void setShowLastViewAsLoading(final boolean pIsShow) {
        if (pIsShow != isShowLastViewAsLoading) {
            isShowLastViewAsLoading = pIsShow;
            notifyDataSetChanged();
        }
    }

    public void addItems(final List<Student> pResult) {
        students.addAll(pResult);
        notifyDataSetChanged();
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(students, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(students, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(int position) {
        students.remove(position);
        notifyItemRemoved(position);
    }

    public Student getSelectedStudent() {
        if(selectPosition == RecyclerView.NO_POSITION)
            return null;

        return students.get(selectPosition);
    }

    public void updateStudent(final Student student){
        if(selectPosition == RecyclerView.NO_POSITION) {
            return;
        }

        Collections.replaceAll(students, students.get(selectPosition), student);
        notifyItemChanged(selectPosition);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @IntDef({ViewType.STUDENT, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {
        int STUDENT = 0;
        int LOADING = 1;
    }

    public class StudentItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public StudentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(selectPosition);
            selectPosition = getAdapterPosition();
            notifyItemChanged(selectPosition);
        }
    }
}
