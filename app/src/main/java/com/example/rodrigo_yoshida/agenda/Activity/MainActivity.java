package com.example.rodrigo_yoshida.agenda.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.rodrigo_yoshida.agenda.Adapter.ContactAdapter;
import com.example.rodrigo_yoshida.agenda.DAO.ContactDAO;
import com.example.rodrigo_yoshida.agenda.Model.Contact;
import com.example.rodrigo_yoshida.agenda.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //Adding list on XML
        ListView listView = (ListView) findViewById(R.id.activity_main_lv_contact);

        ContactDAO dao = new ContactDAO(this);
        List<Contact> contactList = dao.findAll();
        dao.close();

        ContactAdapter adapter = new ContactAdapter(this, contactList);
        listView.setAdapter(adapter);

        //Adding listener on button and put the navigation
        Button addButton = (Button)findViewById(R.id.activity_main_bt_add);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
    }
}
