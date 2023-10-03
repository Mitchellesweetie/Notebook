package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static  final String DB_NAME="mydb.db";
    private static final String ID="id";
    private static final String TABLE_NAME="message_table";
    private  static  final String message="message";

    public DBHelper(@Nullable Context context) {
        super(context,DB_NAME,null,2);
        SQLiteDatabase db=this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table message_table(id integer primary key autoincrement,message TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("drop table if exists message_table");

    }
    public boolean insertMessage(String message){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("message",message);

        long res=db.insert(TABLE_NAME,null,contentValues);
        if (res==1)
            return true;
        else
            return false;
    }
    public ArrayList<MessageInfo> getStudentInfo(){
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<MessageInfo> arrayList=new ArrayList<>();
        Cursor cursor=db.rawQuery("select* from message_table",null);
        while (cursor.moveToNext()){
        int id=cursor.getInt(0);
        String message=cursor.getString(2);
        MessageInfo messageInfo=new MessageInfo(id,message);
        arrayList.add(messageInfo);
        }
        return arrayList;
    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=  db.rawQuery( "select * from message where id="+id+"", null );
        return cursor;
    }

/*    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, message_table);
        return numRows;
    }*/

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public boolean updateMessage(Integer id,String message){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("message",message);

        db.update(TABLE_NAME,contentValues,"id=?",new String[] { Integer.toString(id) } );
        return true;

    }
}

