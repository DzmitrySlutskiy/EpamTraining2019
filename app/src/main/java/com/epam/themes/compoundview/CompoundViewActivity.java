package com.epam.themes.compoundview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.themes.uicomponents.ProfileView;

public class CompoundViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_view);

        final ProfileModel profileModel = getProfileModel();

        final ProfileView profileView = findViewById(R.id.profile_view);
        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileView.updateUserInfo(profileModel);
            }
        });
    }

    private ProfileModel getProfileModel() {
        final ProfileModel profileModel = new ProfileModel();
        profileModel.setName("Любое Денис");
        profileModel.setIcon(R.drawable.ic_supervisor_account_black);

        return profileModel;
    }
}