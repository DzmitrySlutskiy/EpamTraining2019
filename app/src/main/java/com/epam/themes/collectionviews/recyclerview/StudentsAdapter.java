package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.collectionviews.StudentEditView;
import com.epam.themes.collectionviews.StudentView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class StudentsAdapter extends BaseAdapter<Student> {
    private AlertDialog.Builder builder;

    private boolean mIsShowLastViewAsLoading;

    public StudentsAdapter(final Context pContext, final IWebService<Student> service) {
        super(pContext, service);
        builder = new AlertDialog.Builder(pContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup pParent,
                                         @ViewType final int pViewType) {
        if (pViewType == ViewType.STUDENT) {
            return new ViewHolder(new StudentView(pParent.getContext()));
        } else {
            return new ViewHolder(mInflater.inflate(R.layout.layout_progress, pParent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder pViewHolder, final int pPosition) {
        if (getItemViewType(pPosition) == ViewType.STUDENT) {
            final Student student = mEntities.get(pPosition);

            ((StudentView) pViewHolder.itemView).updateStudentInfo(student);

            setOnItemClickListener(pViewHolder.itemView, student);
        }
    }

    @ViewType
    @Override
    public int getItemViewType(final int pPosition) {
        if (pPosition < mEntities.size()) {
            return ViewType.STUDENT;
        } else {
            return ViewType.LOADING;
        }
    }

    @Override
    public int getItemCount() {
        return mIsShowLastViewAsLoading ? mEntities.size() + 1 : mEntities.size();
    }

    public void setShowLastViewAsLoading(final boolean pIsShow) {
        if (pIsShow != mIsShowLastViewAsLoading) {
            mIsShowLastViewAsLoading = pIsShow;
            notifyDataSetChanged();
        }
    }

    public void addItems(final List<Student> pResult) {
        mEntities.addAll(pResult);
        notifyDataSetChanged();
    }

    @IntDef({ViewType.STUDENT, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {
        int STUDENT = 0;
        int LOADING = 1;
    }

    private AlertDialog.Builder buildAlertDialog(final Context context, final Student student) {
        builder.setTitle(R.string.edit_student_dialog_title);
        StudentEditView view = new StudentEditView(context);

        final EditText name = view.updateStudentName(student.getName());
        final EditText hwCount = view.updateStudentHwCount(student.getHwCount());

        builder.setView(view);

        builder.setPositiveButton(R.string.alert_dialog_save_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final Student updatedStudent = new Student();
                updatedStudent.setName(name.getText().toString());
                updatedStudent.setHwCount(Integer.parseInt(hwCount.getText().toString()));
                updatedStudent.setId(student.getId());
                updatedStudent.setIcon(student.getIcon());
                StudentsAdapter.this.mService.update(updatedStudent);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.alert_dialog_close_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder;
    }

    private void setOnItemClickListener(View itemView, final Student student) {
        itemView.findViewById(R.id.student_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildAlertDialog(v.getContext(), student).show();
            }
        });
    }
}
