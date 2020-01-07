package com.kai.kaidong.sqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.superrtc.ContextUtils.getApplicationContext;

public class Helper {

    DBHelper dbHelper;
     SQLiteDatabase readableDatabase;

    public Helper(Context context) {
        dbHelper = new DBHelper(context);
        readableDatabase = dbHelper.getWritableDatabase();
    }

    //添加
     public void add(String name,String password){
         ContentValues values = new ContentValues();
         values.put("name", name);
         values.put("password", password);
         readableDatabase.insert("person",null,values);
     }
   //查询
    public List<Person> getPersons(){
        //任务 完成查询所有数据的方法
        List<Person> persons=new ArrayList<Person>();
        SQLiteDatabase sqLiteDatabase=readableDatabase;
        Cursor cursor=sqLiteDatabase.query("person",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String phone=cursor.getString(cursor.getColumnIndex("password"));
            Person person=new Person(name,phone);
            persons.add(person);
        }
        return persons;
    }
    //删除
    public void delete(String name){
        readableDatabase.delete("person", "name=?", new String[]{name});
    }
    //修改
    public void update(String afterpassword,String beforepassword){
        ContentValues values = new ContentValues();
        values.put("password", afterpassword);
        readableDatabase.update("person", values, "password = ?", new String[]{beforepassword});
    }
}
