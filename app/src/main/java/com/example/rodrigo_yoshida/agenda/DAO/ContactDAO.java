package com.example.rodrigo_yoshida.agenda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rodrigo_yoshida.agenda.Model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigo_yoshida on 02/08/2017.
 */

public class ContactDAO extends SQLiteOpenHelper
{

    public ContactDAO(Context context)
    {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE AGENDA ("
                + "ID INTEGER PRIMARY KEY,"
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
        String sql = "DROP TABLE IF EXIST AGENDA";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Contact contact)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("NAME", contact.getName());
        data.put("ORGANIZATION", contact.getOrganization());
        data.put("TELEPHONE", contact.getTelephone());
        data.put("EMAIL", contact.getEmail());
        data.put("ADDRESS", contact.getAddress());

        db.insert("AGENDA", null, data);
    }

    public List<Contact> findAll()
    {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM AGENDA";
        Cursor cursor = db.rawQuery(sql, null);

        List<Contact> contactList = new ArrayList<Contact>();
        while (cursor.moveToNext())
        {
            Contact contact = new Contact();
            contact.setId(cursor.getLong(cursor.getColumnIndex("ID")));
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
}
