package com.example.quanlysv;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Quanlysv extends Activity {

    Button dx;
    String user ="", pass = "";
    GridView gridButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlysv);

        List<ViewClass> image_details = getListData();
        gridButton = (GridView) findViewById(R.id.gridButton);
        CustomGridAdapter adapter = new CustomGridAdapter(this, image_details);
        try {
            gridButton.setAdapter(adapter);

            // When the user clicks on the GridItem
            gridButton.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    GridView gridButton = (GridView) findViewById(R.id.gridButton);
                    Object o = gridButton.getItemAtPosition(position);
                    ViewClass viewClass = (ViewClass) o;
                    Toast.makeText(Quanlysv.this, "Selected :"
                            + " " + Integer.toString(position), Toast.LENGTH_LONG).show();
                    switch (position){
                        case 0:{
                            //Buttom quản lý lớp
                            Intent intent= new Intent(Quanlysv.this,Quanlylop.class);
                            startActivity(intent);
                            break;
                        }
                        case 1:{
                            //Buttom quản lý sinh viên
                            Intent intent2= new Intent(Quanlysv.this,Quanlysinhvien.class);
                            startActivity(intent2);
                            break;
                        }
                    }
                }
            });

        } catch (Exception ex) {
            Log.e("Error: ", ex.toString());
        }

        dx = (Button) findViewById(R.id.dx);
        showLogin();
        Log.i("Info", user + pass);
//        if (user.length() == 0 && pass.length() == 0){
//            Intent intent3 = new Intent(Quanlysv.this, MainActivity.class);
//            startActivity(intent3);
//        }
        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Quanlysv.this,MainActivity.class);
                startActivity(intent4);
            }
        });
    }
    public void showLogin(){
        SharedPreferences sharedPre = getSharedPreferences("fileLogin", MODE_PRIVATE);
        boolean ck = sharedPre.getBoolean("Savetatus",false);
        if (ck) {
            user = sharedPre.getString("user", "");
            pass = sharedPre.getString("pass", "");

        }

    }
    private List<ViewClass> getListData() {
        List<ViewClass> list = new ArrayList<ViewClass>();
        ViewClass button1 = new ViewClass("Quản lý lớp", "lop", "Quản lý tên lớp, mã lớp");
        ViewClass button2 = new ViewClass("Quản lý sinh viên", "sv", "Chỉnh sửa lưu trữ sinh viên");

        list.add(button1);
        list.add(button2);

        return list;
    }
}
