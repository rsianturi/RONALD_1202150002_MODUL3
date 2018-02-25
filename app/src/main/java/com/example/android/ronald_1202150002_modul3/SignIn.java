package com.example.android.ronald_1202150002_modul3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText editText1 , editText2;
    String text1 ,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void onButtonClicked(View view) {
        editText1=(EditText)findViewById(R.id.username_text);
        editText2=(EditText)findViewById(R.id.password_text);
        text1 = editText1.getText().toString();
        text2 = editText2.getText().toString();

        if ((text1.contains("EAD"))&&((text2.contains("MOBILE")))){
            Toast.makeText(this,"Login Berhasil", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if ((text1.matches("")||text2.matches(""))){
            Toast.makeText(this, "Harus isi Username dan Password",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Username atau Password salah",Toast.LENGTH_SHORT).show();
        }


    }
}
