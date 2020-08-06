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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Quanlylop extends AppCompatActivity {
    Button bntadd , bntedit,bntdelete;
    EditText edid;
    EditText edname;
    TextView tvcount;
    ListView lsView;
    Integer n;
    ArrayList<ClassManager> lsClass = new ArrayList<>();
    ArrayList<ClassManager> arrayAdapter;
    int vitri = -1;
    SharedPreferences sharedPref;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlylop);
        edid = (EditText) findViewById(R.id.edidlop);
        edname = (EditText) findViewById(R.id.edtenlop);
        tvcount = (TextView) findViewById(R.id.tvcount);
        sharedPref = getSharedPreferences("fileLop", MODE_PRIVATE);
        edit = sharedPref.edit();
        loadLop();
        Log.i("Info", Integer.toString(lsClass.size()));
        bntadd = (Button) findViewById(R.id.btnadd);
        bntedit = (Button) findViewById(R.id.btnedit);
        bntdelete = (Button) findViewById(R.id.btndelete);
        lsView = (ListView) findViewById(R.id.lsView);
        final ArrayAdapter<ClassManager> arrayAdapter=
                new ArrayAdapter<ClassManager>(this,android.R.layout.simple_list_item_1,lsClass);
        lsView.setAdapter(arrayAdapter);
        bntadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassManager c = new ClassManager();
                c.setId(edid.getText().toString());
                c.setName(edname.getText().toString());
                lsClass.add(c);
                tvcount.setText("Count : " + lsClass.size());
                arrayAdapter.notifyDataSetChanged();
                edit = sharedPref.edit();
                edit.clear();
                for (ClassManager item : lsClass) {
                    edit.putStringSet("Class" + Integer.toString(lsClass.indexOf(item)), item.toSet());
                }
                edit.putInt("Count", lsClass.size());
                edit.commit();
                edid.setText(" ");
                edname.setText(" ");
            }
        });
        lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Item"+position,Toast.LENGTH_SHORT).show();
                ClassManager c = lsClass.get(position);
                edid.setText(c.getId());
                edname.setText(c.getName());
                vitri = position;
            }
        });
        bntedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassManager c = new ClassManager();
                c.setId(edid.getText().toString());
                c.setName(edname.getText().toString());
                lsClass.set(vitri,c);
                edit = sharedPref.edit();
                edit.clear();
                for (ClassManager item : lsClass) {
                    edit.putStringSet("Class" + Integer.toString(lsClass.indexOf(item)), item.toSet());
                }
                edit.putInt("Count", lsClass.size());
                edit.commit();
                arrayAdapter.notifyDataSetChanged();
            }
        });
        bntdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edid.setText(" ");
                edname.setText(" ");
                lsClass.remove(vitri);
                edit = sharedPref.edit();
                edit.clear();
                for (ClassManager item : lsClass) {
                    edit.putStringSet("Class" + Integer.toString(lsClass.indexOf(item)), item.toSet());
                }
                edit.putInt("Count", lsClass.size());
                edit.commit();
                arrayAdapter.notifyDataSetChanged();
                tvcount.setText("Count : " + lsClass.size());
            }
        });
    }
    private void loadLop(){
        SharedPreferences sharedPref = getSharedPreferences("fileLop", MODE_PRIVATE);
        n = sharedPref.getInt("Count", 0);
        if(n == 0){
            lsClass.add(new ClassManager("HT01","He Thong 1"));
            lsClass.add(new ClassManager("HT02","He Thong 2"));
            lsClass.add(new ClassManager("PM01","Phan Mem 1"));
            lsClass.add(new ClassManager("PM02","Phan Mem 1"));
            edit.clear();
            for (ClassManager item : lsClass) {
                edit.putStringSet("Class" + Integer.toString(lsClass.indexOf(item)), item.toSet());
            }
            edit.putInt("Count", lsClass.size());
            edit.commit();
        } else {
            Log.i("Info", Integer.toString(n));
            Set<String> de = new HashSet<>();
            for(int i = 0; i < n; ++i){
                lsClass.add(new ClassManager(sharedPref.getStringSet("Class" + Integer.toString(i), de)));
            }
        }

    }
}