package com.epam.themes.collectionviews.recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
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
            View view1 = new StudentView(parent.getContext());
            return new MyViewHolder(view1, new MyViewHolder.IMyViewHolderClicks() {
                @Override
                public void onMyItemClicked(View caller) {
                    showDialog(caller);
                }
            });
        } else {
            return new BaseViewHolder<>(inflater.inflate(R.layout.layout_progress, parent, false));
        }
    }

    private void showDialog(final View caller) {
        final EditText edittext = new EditText(context);
        new AlertDialog.Builder(context)
                .setTitle("ok")
                .setMessage("set count Hw student  input is number(int)")
                .setView(edittext)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int newCountHw = Integer.parseInt(edittext.getText().toString());
                        try {
                            studentsList.get(Integer.parseInt(((StudentView) caller).getStudentNameStr())).setHwCount(newCountHw);
                        } catch (Exception e) {
                            Log.e(TAG, "error in edit count hw");
                        }
                        notifyDataSetChanged();
                    }
                })
                .setIcon(android.R.drawable.stat_notify_error)
                .show();

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder pViewHolder, final int position) {
        if (getItemViewType(position) == ViewType.STUDENT) {
            final Student student = studentsList.get(position);
            ((StudentView) pViewHolder.itemView)
                    .setStudentName(student.getName())
                    .setStudentAge(String.valueOf(student.getHwCount()));
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

    public void addItems(final List<Student> pResult) {
        studentsList.addAll(pResult);
        notifyDataSetChanged();
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

        IMyViewHolderClicks listener;

        MyViewHolder(View itemLayoutView, IMyViewHolderClicks listener) {
            super(itemLayoutView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onMyItemClicked(v);
            Log.e(TAG, "item clicked ");
        }

        public interface IMyViewHolderClicks {
            void onMyItemClicked(View caller);
        }
    }
}
