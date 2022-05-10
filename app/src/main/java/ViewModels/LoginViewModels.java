package ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;
import com.pdhn.usersjava.R;
import com.pdhn.usersjava.VerifyEmail;
import com.pdhn.usersjava.VerifyPassword;
import com.pdhn.usersjava.databinding.VerifyEmailBinding;
import com.pdhn.usersjava.databinding.VerifyPasswordBinding;

import Interfaces.IonClick;
import Library.Networks;
import Library.Validate;
import Models.BindableString;

public class LoginViewModels extends ViewModel implements IonClick {

    private Activity _activity;
    public static String emailData=null;
    private  static VerifyEmailBinding _bindingEmail;
    private static VerifyPasswordBinding _bindingPassword;
    public BindableString emailUI= new BindableString();
    public BindableString passwordUI = new BindableString();

    public LoginViewModels(
            Activity activity,
            VerifyEmailBinding bindingEmail,
            VerifyPasswordBinding bindingPassword
    ) {
        _activity = activity;
        _bindingEmail = bindingEmail;
        _bindingPassword = bindingPassword;
        if (emailData != null){
            emailUI.setValue(emailData);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.email_sign_in_button:
                VerifyEmail();
                break;
            case R.id.password_sign_in_button:
                login();
                break;
        }
       // Toast.makeText(_activity,emailUI.getValue(),Toast.LENGTH_SHORT).show();
    }
    private void VerifyEmail(){
        boolean cancel = true;
        _bindingEmail.emailEditText.setError(null);
        if (TextUtils.isEmpty(emailUI.getValue())){
            _bindingEmail.emailEditText.setError(_activity.getString(R.string.error_field_required));
            _bindingEmail.emailEditText.requestFocus();
            cancel = false;
        } else if (!Validate.isEmail(emailUI.getValue())){
            _bindingEmail.emailEditText.setError(_activity.getString(R.string.error_invalid_required));
            _bindingEmail.emailEditText.requestFocus();
            cancel = false;
        }
        if (cancel){
            emailData = emailUI.getValue();
            _activity.startActivity(new Intent(_activity, VerifyPassword.class));
        }
    }
    private void login(){
        boolean cancel = true;
        _bindingPassword.passwordEditText.setError(null);
        if (TextUtils.isEmpty(passwordUI.getValue())){
            _bindingPassword.passwordEditText.setError(
                    _activity.getString(R.string.error_field_required));
            cancel= false;
        }else if (!isPasswordValid(passwordUI.getValue())) {
            _bindingPassword.passwordEditText.setError(_activity.getString(R.string.error_invalid_password));
            cancel = false;
        }
        if (cancel){
            if (new Networks(_activity).verificaNetworks()){

            }else{
                Snackbar.make(_bindingPassword.passwordEditText,R.string.networks,Snackbar.LENGTH_LONG).show();
            }
        }
    }
    private boolean isPasswordValid(String password){
        return password.length()>= 6;
    }
}


