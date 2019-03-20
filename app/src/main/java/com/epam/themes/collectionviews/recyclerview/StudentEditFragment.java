package com.epam.themes.collectionviews.recyclerview;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.epam.themes.collectionviews.StudentsActivity;
import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Student;

public class StudentEditFragment extends DialogFragment {
    private static final String STUDENT_ARG = "student_argument";

    private Student updatedStudent;

    public static StudentEditFragment newInstance(Student student) {
        StudentEditFragment dialog = new StudentEditFragment();
        Bundle args = new Bundle();
        StudentParcelable studentParcelable =
            new StudentParcelable(student.getId(), student.getName(), student.getEmail(), student.getAddress());
        args.putParcelable(STUDENT_ARG, studentParcelable);
        dialog.setArguments(args);

        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_edit_student_dialog, null);
        updateViewData(view);
        builder.setView(view).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            @Override
                public void onClick(DialogInterface dialog, int id) {
                    EditText studentName =  view.findViewById(R.id.studentName);
                    updatedStudent.setName(studentName.getText().toString());
                    EditText studentEmail =  view.findViewById(R.id.studentEmail);
                    updatedStudent.setEmail(studentEmail.getText().toString());
                    ((StudentsActivity)getActivity()).updateStudent(updatedStudent);
                 }
            })
            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    StudentEditFragment.this.getDialog().cancel();
                }
            });

        return builder.create();
    }

    private void updateViewData(View view) {
        StudentParcelable studentParcelable = getArguments().getParcelable(STUDENT_ARG);
        EditText studentName =  view.findViewById(R.id.studentName);
        studentName.setText(studentParcelable.getStudentName());
        EditText studentEmail =  view.findViewById(R.id.studentEmail);
        studentEmail.setText(studentParcelable.getStudentEmail());

        updatedStudent = new Student();
        updatedStudent.setId(studentParcelable.getStudentId());
        updatedStudent.setAddress(studentParcelable.getStudentAddress());
    }
}
