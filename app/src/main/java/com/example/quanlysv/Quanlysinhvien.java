package com.example.quanlysv;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Quanlysinhvien extends AppCompatActivity {
    Button bntadd2 , bntedit2,bntdelete2;
    EditText tenSV, maSV , Age,email,phone,address;
    TextView tvcount2;
    ListView lsView2;
    ArrayList<Student> lsStudent = new ArrayList<>();
    ArrayList<Student> arrayAdapter;
    ArrayAdapter aa;
    Integer n;
    ArrayList<ClassManager> lop = new ArrayList<>();
    Spinner spinner;
    int vitri = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlysinhvien);
        SharedPreferences sharedPref = getSharedPreferences("fileLop", MODE_PRIVATE);
        n = sharedPref.getInt("Count", 0);
        Log.i("Info", Integer.toString(n));
        Set<String> de = new HashSet<>();
        for(int i = 0; i < n; ++i){
            lop.add(new ClassManager(sharedPref.getStringSet("Class" + Integer.toString(i), de)));
        }
        tenSV = (EditText) findViewById(R.id.tensv);
        maSV = (EditText) findViewById(R.id.masv);
        Age = (EditText) findViewById(R.id.age);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        address = (EditText) findViewById(R.id.address);
        tvcount2 = (TextView) findViewById(R.id.tvcount2);

        bntadd2 = (Button) findViewById(R.id.btnadd2);
        bntedit2 = (Button) findViewById(R.id.btnedit2);
        bntdelete2 = (Button) findViewById(R.id.btndelete2);
        lsView2 = (ListView) findViewById(R.id.lsView2);
        spinner = (Spinner) findViewById(R.id.spinner);
        aa = new ArrayAdapter<ClassManager>(this, android.R.layout.simple_spinner_item, lop);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);

        final ArrayAdapter<Student> arrayAdapter =
                new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, lsStudent);
        lsView2.setAdapter(arrayAdapter);
        bntadd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student st = new Student();
                st.setTenSV(tenSV.getText().toString());
                st.setMaSV(maSV.getText().toString());
                st.setLop(spinner.getItemAtPosition(spinner.getSelectedItemPosition()) + "");
                st.setAge(Age.getText().toString());
                st.setEmail(email.getText().toString());
                st.setPhone(phone.getText().toString());
                st.setDiaChi(address.getText().toString());
                lsStudent.add(st);
                tvcount2.setText("Count : "+lsStudent.size());
                arrayAdapter.notifyDataSetChanged();
                tenSV.setText(" ");
                maSV.setText(" ");
                Age.setText(" ");
                email.setText(" ");
                phone.setText(" ");
                address.setText(" ");
            }
        });
        lsView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Item"+position,Toast.LENGTH_SHORT).show();
                Student st = lsStudent.get(position);
                tenSV.setText(st.getTenSV());
                maSV.setText(st.getMaSV());
                Age.setText(st.getAge());
                email.setText(st.getEmail());
                phone.setText(st.getPhone());
                address.setText(st.getDiaChi());
                vitri = position;
            }
        });
        bntedit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student st = new Student();
                st.setTenSV(tenSV.getText().toString());
                st.setMaSV(maSV.getText().toString());
                st.setLop(spinner.getItemAtPosition(spinner.getSelectedItemPosition()) + "");
                st.setAge(Age.getText().toString());
                st.setEmail(email.getText().toString());
                st.setPhone(phone.getText().toString());
                st.setDiaChi(address.getText().toString());
                lsStudent.set(vitri,st);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        bntdelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenSV.setText(" ");
                maSV.setText(" ");
                Age.setText(" ");
                email.setText(" ");
                phone.setText(" ");
                address.setText(" ");
                lsStudent.remove(vitri);
                arrayAdapter.notifyDataSetChanged();
                tvcount2.setText("Count : "+lsStudent.size());
            }
        });
    }
}