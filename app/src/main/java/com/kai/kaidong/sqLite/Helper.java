package com.kai.kaidong.sqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class Helper {

    DBHelper dbHelper;
     SQLiteDatabase readableDatabase;

    public Helper(Context context) {
        dbHelper = new DBHelper(context);
        readableDatabase = dbHelper.getWritableDatabase();
    }

    //添加
     public void add(String name,String password,String phone){
         ContentValues values = new ContentValues();
         values.put("name", name);
         values.put("password", password);
         values.put("phone", phone);
         readableDatabase.insert("person",null,values);
         Log.i("TAG","添加");
     }
   //查询
    public List<Person> getPersons(){
        //任务 完成查询所有数据的方法
        List<Person> persons=new ArrayList<Person>();
        SQLiteDatabase sqLiteDatabase=readableDatabase;
        Cursor cursor=sqLiteDatabase.query("person",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String phone=cursor.getString(cursor.getColumnIndex("phone"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            Person person=new Person(name,password,phone);
            persons.add(person);
        }
        Log.i("TAG","查询");
        return persons;
    }
    //删除
    public void delete(String name){
        readableDatabase.delete("person", "name=?", new String[]{name});
        Log.i("TAG","删除");
    }
    //修改
    public void update(String afterpassword,String beforepassword){
        ContentValues values = new ContentValues();
        values.put("password", afterpassword);
        readableDatabase.update("person", values, "password = ?", new String[]{beforepassword});
        Log.i("TAG","修改");
    }
}
