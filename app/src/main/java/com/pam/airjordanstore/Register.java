package com.pam.airjordanstore;

import static com.pam.airjordanstore.Login.fromHtml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText TxUsername, TxPassword, TxRePassword;
    Button BtnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        TxUsername = (EditText) findViewById(R.id.txUsernameReg);
        TxPassword = (EditText) findViewById(R.id.txPasswordReg);
        TxRePassword = (EditText) findViewById(R.id.txRePassword);
        BtnRegister = (Button) findViewById(R.id.bntRegister);

        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml("Back to " + "</font><font color='#3b5998'>Login</font>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();
                String rePassword = TxRePassword.getText().toString().trim();

                ContentValues values = new ContentValues();

                if (!password.equals(rePassword)){
                    Toast.makeText(Register.this, "Password not match", Toast.LENGTH_SHORT).show();
                }else if (password.equals("") || username.equals("")){
                    Toast.makeText(Register.this, "Username or Password cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(DBHelper.row_username, username);
                    values.put(DBHelper.row_password, password);
                    dbHelper.insertData(values);

                    Toast.makeText(Register.this, "Register successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}