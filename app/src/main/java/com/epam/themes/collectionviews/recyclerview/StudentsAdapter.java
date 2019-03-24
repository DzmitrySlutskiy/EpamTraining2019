package com.epam.themes.collectionviews.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.uicomponents.StudentView;
import com.epam.themes.uicomponents.base.BaseViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = StudentsAdapter.class.getSimpleName();
    private boolean mIsShowLastViewAsLoading = false;
    private Context context;
    private final LayoutInflater inflater;
    private final List<Student> studentsList = new ArrayList<>();

    public StudentsAdapter(final Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                                      @ViewType final int viewType) {
        if (viewType == ViewType.STUDENT) {
            View studentView = new StudentView(parent.getContext());
            return new MyViewHolder(studentView, new MyViewHolder.IMyViewHolderClicks() {
                @Override
                public void onMyItemClicked(String nameStudent) {
                    showDialog(nameStudent);
                }
            });
        } else {
            return new BaseViewHolder<>(inflater.inflate(R.layout.layout_progress, parent, false));
        }
    }

    private void showDialog(final String nameStudent) {
        final EditText edittext = new EditText(context);
        new AlertDialog.Builder(context)
                .setTitle("ok")
                .setMessage("set count Hw student  input is number(int)")
                .setView(edittext)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            int newCountHw = Integer.parseInt(edittext.getText().toString());
                            List<Student> newStudentList = new ArrayList<>(studentsList);
                            newStudentList.get(Integer.parseInt(nameStudent)).setHwCount(newCountHw);
                            updateItems(newStudentList);
                        } catch (Exception e) {
                            Log.e(TAG, "error in edit count hw");
                        }
                    }
                })
                .setIcon(android.R.drawable.stat_notify_error)
                .show();

    }

    private void updateItems(final List<Student> newStudentsList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(newStudentsList, studentsList));
        diffResult.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }

    public class MyDiffCallback extends DiffUtil.Callback {

        List<Student> oldStudent;
        List<Student> newStudent;

        MyDiffCallback(List<Student> newStudents, List<Student> oldStudents) {
            this.newStudent = newStudents;
            this.oldStudent = oldStudents;
        }

        @Override
        public int getOldListSize() {
            return oldStudent.size();
        }

        @Override
        public int getNewListSize() {
            return newStudent.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldStudent.get(oldItemPosition).getId().equals(newStudent.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldStudent.get(oldItemPosition).equals(newStudent.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        if (getItemViewType(position) == ViewType.STUDENT) {
            final Student student = studentsList.get(position);
            ((StudentView) viewHolder.itemView)
                    .setStudentName(student.getName())
                    .setStudentAge(String.valueOf(student.getHwCount()));
            ((MyViewHolder) viewHolder).bindStudent(student.getName());
        }
    }

    @ViewType
    @Override
    public int getItemViewType(final int position) {
        if (position < studentsList.size()) {
            return ViewType.STUDENT;
        } else {
            return ViewType.LOADING;
        }
    }


    @Override
    public int getItemCount() {
        if (mIsShowLastViewAsLoading) {
            return studentsList.size() + 1;
        } else {
            return studentsList.size();
        }
    }

    public void setShowLastViewAsLoading(final boolean pIsShow) {
        if (pIsShow != mIsShowLastViewAsLoading) {
            mIsShowLastViewAsLoading = pIsShow;
            notifyDataSetChanged();
        }
    }

    public void addItems(final List<Student> resultList) {
        studentsList.addAll(resultList);
        updateItems(studentsList);
    }

    void onItemMove(int adapterPositionFrom, int adapterPosition1To) {
        if (adapterPositionFrom < adapterPosition1To) {
            for (int i = adapterPositionFrom; i < adapterPosition1To; i++) {
                Collections.swap(studentsList, i, i + 1);
            }
        } else {
            for (int i = adapterPositionFrom; i > adapterPosition1To; i--) {
                Collections.swap(studentsList, i, i - 1);
            }
        }
        notifyItemMoved(adapterPositionFrom, adapterPosition1To);

    }

    void onItemDismiss(int adapterPosition) {
        deleteByIndex(adapterPosition);
    }

    private void deleteByIndex(int i) {
        studentsList.remove(i);
        notifyItemRemoved(i);
    }


    @IntDef({ViewType.STUDENT, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {
        int STUDENT = 0;
        int LOADING = 1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private String nameStudent;
        private IMyViewHolderClicks listener;

        MyViewHolder(View itemLayoutView, IMyViewHolderClicks listener) {
            super(itemLayoutView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindStudent(String nameStudent) {
            this.nameStudent = nameStudent;
        }

        @Override
        public void onClick(View v) {
            listener.onMyItemClicked(nameStudent);
            Log.e(TAG, "item clicked ");
        }

        public interface IMyViewHolderClicks {
            void onMyItemClicked(String nameStudent);
        }
    }
}
