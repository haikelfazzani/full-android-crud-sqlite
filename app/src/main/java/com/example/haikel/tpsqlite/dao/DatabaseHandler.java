package com.example.haikel.tpsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.haikel.tpsqlite.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="ContactManager";

    private static final String TABLE_NAME="contact";
    private static final String KEY_ID="id" ;
    private static final String KEY_NAME="nom" ;
    private static final String KEY_TEL="tel" ;

    private final static String CREATE_TABLE= "CREATE TABLE "+TABLE_NAME+
            " ("+KEY_ID+" integer primary key autoincrement," +
            KEY_NAME +" text not null, "+KEY_TEL+" text not null)";

    private SQLiteDatabase myDB;

    public DatabaseHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table " + DATABASE_NAME);
            this.onCreate(db);
        }
    }

    public SQLiteDatabase openDB() {
        myDB = this.getWritableDatabase();
        return myDB;
    }

    public void closeDB() { myDB.close(); }

    public long addContact(Contact contact) {
        myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_TEL, contact.getTel());
        return myDB.insert(TABLE_NAME, null, contentValues);
    }

    public List<Contact> getAllContact() {
        List<Contact> contactList = new ArrayList<>();
        myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM "+TABLE_NAME, null);

        if(cursor.getCount() == 0) return new ArrayList<>(0);
        cursor.moveToFirst();

            while(cursor.moveToNext()) {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setTel(cursor.getString(2));
                contactList.add(contact);
            }

        cursor.close();
        return contactList;
    }

    public void updateContact(Contact contact) {
        myDB = this.getWritableDatabase();
        String sql = "UPDATE "+TABLE_NAME+" SET "+KEY_TEL +" = ? WHERE " +KEY_NAME +" = ?";
        Cursor cursor = myDB.rawQuery(sql,
                new String[]{contact.getTel(), contact.getName()});
        cursor.moveToFirst();
    }

    public void deleteContact(String name) {
        myDB = this.getWritableDatabase();
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+KEY_NAME + " = ?";
        Cursor cursor = myDB.rawQuery(sql, new String[]{name});
        cursor.moveToFirst();
        cursor.close();
    }
}
