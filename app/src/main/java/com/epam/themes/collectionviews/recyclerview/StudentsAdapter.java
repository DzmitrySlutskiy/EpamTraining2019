package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.StudentsWebService;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.uicomponents.LessonView;
import com.epam.themes.uicomponents.base.BaseViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private boolean mIsShowLastViewAsLoading = false;

    private final LayoutInflater mInflater;
    private final List<Student> mStudents = new ArrayList<>();
    private final IWebService<Student> iWebService = new StudentsWebService();

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

            pViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editStudentDialog(view, pPosition);
                }
            });
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

    public void addItem(Student student) {
        iWebService.addEntity(student);
        mStudents.add(student);
        notifyDataSetChanged();
    }

    private void deleteByIndex(int i) {
        iWebService.removeEntity(i);
        mStudents.remove(i);
        notifyItemRemoved(i);
    }

    private void editItem(int i, String name, int hwCount) {
        final Student student = mStudents.get(i);
        student.setName(name);
        student.setHwCount(hwCount);
        iWebService.editEntity(i, name, hwCount);
        notifyItemChanged(i);
    }

    void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mStudents, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mStudents, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    void onItemDismiss(int adapterPosition) {
        deleteByIndex(adapterPosition);
    }

    @IntDef({ViewType.STUDENT, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {

        int STUDENT = 0;
        int LOADING = 1;
    }

    private void editStudentDialog(View view, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View dialogView = mInflater.inflate(R.layout.dialog_edit_student, null);

        final TextInputLayout nameText = dialogView.findViewById(R.id.edit_name_view);
        final TextInputLayout hwCountText = dialogView.findViewById(R.id.edit_hw_view);

        builder.setView(dialogView)
                .setTitle(mStudents.get(position).getName())
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name = Objects.requireNonNull(nameText.getEditText()).getText().toString();
                        int hwCount = Integer.parseInt(Objects.requireNonNull(hwCountText.getEditText()).getText().toString());

                        editItem(position, name, hwCount);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }
}
