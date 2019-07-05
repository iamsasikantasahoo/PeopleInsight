package com.people.survey.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.people.survey.R;
import com.people.survey.databinding.LoginActivityBinding;
public class LoginActivity extends AppCompatActivity {
    private LoginActivityBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.login_activity);
      //  activityBinding.userNameLayout.setError("Error");
    }

}
