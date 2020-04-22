package com.example.sqllitewithrecylcerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class connection extends SQLiteOpenHelper
{
    private  static  final  String Database_Name="Database.db";
    private String userTable="create table user(name varchar(30), rollno varchar(30))";

    private  Context context;
    public connection(@Nullable Context context) {
        super(context, Database_Name,null, 1);
    this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
           db.execSQL(userTable);
            Toast.makeText(context,"Database and Table Created",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(context,"onCreateDb:"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
