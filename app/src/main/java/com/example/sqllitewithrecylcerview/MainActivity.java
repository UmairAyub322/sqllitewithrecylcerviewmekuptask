package com.example.sqllitewithrecylcerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 private Button dbbutton,addvalues,get;
 private ArrayList<modelclass> modelclassArrayList;
 private EditText name,rollno;

 connection object;
 private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    Intialize();

    }
    private void Intialize(){
        try {

            recyclerView=findViewById(R.id.RV);
            modelclassArrayList= new ArrayList<>();
            addvalues=findViewById(R.id.add);
            name=findViewById(R.id.name);
            rollno=findViewById(R.id.rollno);
            dbbutton=findViewById(R.id.BT1);
            object=new connection(this);
            get=findViewById(R.id.view);
            get.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getvalues();
                }
            });
            addvalues.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addvalues();
                }
            });
            dbbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createdb();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this,"Connect XML"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void createdb(){
        try {
            object.getReadableDatabase();
        }
        catch (Exception e){
            Toast.makeText(this,"createDb"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void addvalues(){
        try {
            if(!name.getText().toString().isEmpty()&&!rollno.getText().toString().isEmpty()){
                SQLiteDatabase objectdb=object.getWritableDatabase();
                if(objectdb!=null){
                    ContentValues contentValues= new ContentValues();
                    contentValues.put("name",name.getText().toString());
                    contentValues.put("rollno",rollno.getText().toString());

                    long temp=objectdb.insert("user",null,contentValues);
                    if (temp!=-1){
                        Toast.makeText(this,"Values inserted Successfully",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    rollno.setText("");
                    name.requestFocus();
                    }
                    else {
                        Toast.makeText(this,"Values not inserted",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this,"Database object is null",Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(this,"please fill all fields",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this,"addValues"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private void getvalues(){
        try {
            SQLiteDatabase objectdb=object.getReadableDatabase();
            if(objectdb!=null){
                Cursor cursor =objectdb.rawQuery("select * from user",null);
                StringBuffer stringBuffer= new StringBuffer();

                if(cursor.getCount()!=0){
                    while (cursor.moveToNext()){
                        modelclass objectmodelclass= new modelclass();
                        objectmodelclass.setName(cursor.getString(0));
                        objectmodelclass.setRollno(cursor.getString(1));

                        modelclassArrayList.add(objectmodelclass);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        adpter objectad = new adpter(modelclassArrayList);
                        recyclerView.setAdapter(objectad);

                    }
                }
            }
            else {
                Toast.makeText(this,"DB is null",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
