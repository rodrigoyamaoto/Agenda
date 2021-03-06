package com.example.rodrigo_yoshida.agenda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rodrigo_yoshida.agenda.Model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends SQLiteOpenHelper
{

    public ContactDAO(Context context)
    {
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE AGENDA ("
                + "ID INTEGER PRIMARY KEY,"
                + "PATH_PHOTO TEXT,"
                + "NAME TEXT NOT NULL,"
                + "ORGANIZATION TEXT,"
                + "TELEPHONE TEXT,"
                + "EMAIL TEXT,"
                + "ADDRESS TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sql;
        switch (oldVersion){
            case 1:
                sql = "ALTER TABLE AGENDA ADD COLUMN PATH_PHOTO TEXT";
                db.execSQL(sql);
        }
    }

    public void insert(Contact contact)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = getContentValues(contact);
        db.insert("AGENDA", null, contentValues);
    }

    public void delete(Contact contact)
    {
        SQLiteDatabase db = getWritableDatabase();
        String[] parametro = {contact.getId().toString()};
        db.delete("AGENDA", "ID = ?", parametro);
    }

    public void update(Contact contact)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = getContentValues(contact);
        String[] parametro = {contact.getId().toString()};

        db.update("AGENDA", contentValues, "ID = ?", parametro);
    }

    public List<Contact> findAll()
    {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM AGENDA";
        Cursor cursor = db.rawQuery(sql, null);

        List<Contact> contactList = new ArrayList<Contact>();
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex("ID")));
            contact.setPathPhoto(cursor.getString(cursor.getColumnIndex("PATH_PHOTO")));
            contact.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            contact.setOrganization(cursor.getString(cursor.getColumnIndex("ORGANIZATION")));
            contact.setTelephone(cursor.getString(cursor.getColumnIndex("TELEPHONE")));
            contact.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
            contact.setAddress(cursor.getString(cursor.getColumnIndex("ADDRESS")));

            contactList.add(contact);
        }
        cursor.close();
        return contactList;
    }

    private ContentValues getContentValues(Contact contact)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("PATH_PHOTO", contact.getPathPhoto());
        contentValues.put("NAME", contact.getName());
        contentValues.put("ORGANIZATION", contact.getOrganization());
        contentValues.put("TELEPHONE", contact.getTelephone());
        contentValues.put("EMAIL", contact.getEmail());
        contentValues.put("ADDRESS", contact.getAddress());

        return contentValues;
    }
}
