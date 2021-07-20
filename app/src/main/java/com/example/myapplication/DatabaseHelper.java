package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9876787654,'Ron',9972.00,'ron193@gmail.com','XXXXXXXXXXXX7894','ICI09876543')");
        db.execSQL("insert into user_table values(9080706544,'Amisha',5882.67,'amisha235@gmail.com','XXXXXXXXXXXX2991','VIJ98765432')");
        db.execSQL("insert into user_table values(8765678769,'Manpreet',399.56,'manp168@gmail.com','XXXXXXXXXXXX787','DEK87654321')");
        db.execSQL("insert into user_table values(9870708060,'Deepak',500.91,'Deep509@gmail.com','XXXXXXXXXXXX4879','ICI76543210')");
        db.execSQL("insert into user_table values(9898675645,'Josh',2690.48,'Joshin003@gmail.com','XXXXXXXXXXXX2885','BOI65432109')");
        db.execSQL("insert into user_table values(9875678997,'Alina',9459.16,'alina@gmail.com','XXXXXXXXXXXX3444','IND54321098')");
        db.execSQL("insert into user_table values(9988007654,'Ash',593.00,'ashdek22@gmail.com','XXXXXXXXXXXX0909','TXC43210987')");
        db.execSQL("insert into user_table values(9875678900,'Trixcy',8057.22,'trixcyin983@gmail.com','XXXXXXXXXXXX6534','BAC32109876')");
        db.execSQL("insert into user_table values(8989007654,'Ani',439.96,'ani787@gmail.com','XXXXXXXXXXXX3400','TUV21098765')");
        db.execSQL("insert into user_table values(9876546789,'Dhruv',279.00,'dhruv318@gmail.com','XXXXXXXXXXXX4567','UXC810987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
