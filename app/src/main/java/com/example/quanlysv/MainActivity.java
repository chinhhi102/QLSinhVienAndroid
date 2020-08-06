package com.example.quanlysv;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edituser, editpass;
    ImageView imgadmin, imguser;
    Button bntadmin, bntuser;
    Button bntlogin, bnthuy;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edituser = (EditText) findViewById(R.id.edituser);
        editpass = (EditText) findViewById(R.id.editpass);
        imgadmin = (ImageView) findViewById(R.id.imgviewanh);
        imguser = (ImageView) findViewById(R.id.imgviewanh);
        check = (CheckBox) findViewById(R.id.check);
        bntlogin = (Button) findViewById(R.id.bntlogin);
        bnthuy = (Button) findViewById(R.id.bnthuy);
        bntadmin = (Button) findViewById(R.id.bntadmin);
        bntuser = (Button) findViewById(R.id.bntuser);
        bnthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có thật sự muốn thoát khỏi app");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận ");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        bntlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = edituser.getText().toString();
                String p = editpass.getText().toString();
                boolean ck = check.isChecked();
                if (u.equalsIgnoreCase("admin") && p.equalsIgnoreCase("admin")) {
                    saveLogin(u, p);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Sai tin đăng nhập hoặc mật khẩu", Toast.LENGTH_LONG).show();
                }
            }
        });
        bntadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgadmin.setImageResource(R.drawable.admin);
            }
        });
        bntuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imguser.setImageResource(R.drawable.user);
            }
        });
    }

    public void saveLogin(String user, String pass) {
        SharedPreferences sharedPre = getSharedPreferences("fileLogin", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPre.edit();
        String u = edituser.getText().toString();
        String p = editpass.getText().toString();
        boolean ck = check.isChecked();
        if (!ck) {
            edit.clear();
        } else {
            edit.putString("user", user);
            edit.putString("pass", pass);
            edit.putBoolean("Savetatus", ck);
        }
        edit.commit();
    }

}
