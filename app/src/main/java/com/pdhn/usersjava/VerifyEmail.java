package com.pdhn.usersjava;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.pdhn.usersjava.databinding.VerifyEmailBinding;

import ViewModels.LoginViewModels;

public class VerifyEmail extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

       // setContentView(R.layout.verify_email);

        VerifyEmailBinding _bindingEmail = DataBindingUtil.setContentView(this,R.layout.verify_email);
        _bindingEmail.setEmailModel(new LoginViewModels(this,_bindingEmail, null));
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent,null));



    }
}