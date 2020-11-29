package com.example.internlogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.internlogin.api.ApiUtils;
import com.example.internlogin.interfaced.APIService;
import com.example.internlogin.modelOfResponse.GetLogin.GetUser;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //Save username
    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";

    private final String defaultUserIdentity = "";
    private String userIdentity;



    private String s_password, s_tckNo;
    CheckBox checkBox;
    EditText et_userTcNo, et_userPassword;
    TextView tv_warningTckn,tv_warningPassword;
    Button btn_forgetPassText;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assign();
        loadPreferences();
        apiService = ApiUtils.getAPIService();
    }

    private void getUserRequest(Map<String,String> params) {
        System.out.println("get user request");
        apiService.userPost(params).enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                if(response.isSuccessful()) {
                    if(response.body().getStatus() == true) {

                       passMyAccountActivity(response.body().getIdentityId());
                    }
                    else {
                        alertDialogOpen();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                alertDialogOpen();
                System.out.println("User Request : " + t.getMessage());
            }
        });
    }

    private void assign() {
        checkBox = findViewById(R.id.checkBox);


        et_userPassword = findViewById(R.id.et_userPassword);
        et_userTcNo = findViewById(R.id.et_userTcNo);
        tv_warningTckn = findViewById(R.id.tv_warningTckn);
        tv_warningPassword = findViewById(R.id.tv_warningPassword);
        btn_forgetPassText = findViewById(R.id.btn_forgetPassText);
    }

    //checkbox tıklanırsa
    public void clickRememberMe(View view) {

        checkBox.setClickable(true);

        savePreferences();
    }


    //Username ve checkbox tıklandığını tut.
    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        userIdentity = et_userTcNo.getText().toString();

        editor.putString(PREF_UNAME, userIdentity);

        editor.commit();
    }

    //Sayfa açılırken kayıtlı adı çek
    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        userIdentity = settings.getString(PREF_UNAME, defaultUserIdentity);
        et_userTcNo.setText(userIdentity);

    }

    //Kayıt olma işlemi için, kayıt ekranına yönlendiriyor.
    public void clickSignupButton(View view) {
        resetTextviewWarning();
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void clickSigninButton(View view) {
        resetTextviewWarning();
        loadEditTextInfo();

        if (!s_tckNo.equals("")) {
            if (!s_password.equals("")) {
                System.out.println("map oluşturulurdu.");
                Map<String,String > param = new HashMap<>();
                param.put("kullaniciAdi","string");
                param.put("kullaniciKimlikNumarasi",s_tckNo);
                param.put("kullaniciSifre",s_password);

                getUserRequest(param);
            } else {

                Toast.makeText(getApplicationContext(), Html.fromHtml("<font color='#ce2900' ><b>" + "Lütfen geçerli bir şifre giriniz." + "</b></font>"),Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(getApplicationContext(), Html.fromHtml("<font color='#ce2900' ><b>" + "Lütfen geçerli bir TC kimlik numarası giriniz." + "</b></font>"),Toast.LENGTH_SHORT).show();
        }
    }

    private void loadEditTextInfo() {
        s_tckNo = et_userTcNo.getText().toString();
        s_password = et_userPassword.getText().toString();

    }

    private void alertDialogOpen() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_layout_wrong_info, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        Button btn_yes = view.findViewById(R.id.btn_yes);
        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
        resetEditTexts();
    }

    private void resetEditTexts() {
        et_userPassword.setText("");
        et_userTcNo.setText("");
    }

    private void resetTextviewWarning(){
        tv_warningPassword.setVisibility(View.GONE);
        tv_warningTckn.setVisibility(View.GONE);
        btn_forgetPassText.setVisibility(View.VISIBLE);
    }

    private void openWarningTextView(final String message, TextView textView) {
        btn_forgetPassText.setVisibility(View.GONE);
        textView.setText(message);
        textView.setVisibility(View.VISIBLE);
    }

    private void passMyAccountActivity(String userIdentitiy){
        Intent intent = new Intent(this, MyAccount.class);
        intent.putExtra("userIdentity",userIdentitiy);
        startActivity(intent);
    }

}
