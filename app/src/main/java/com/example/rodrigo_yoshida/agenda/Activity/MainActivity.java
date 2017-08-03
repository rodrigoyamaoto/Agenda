package com.example.rodrigo_yoshida.agenda.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.activity_main_lv_contact);
        Button addButton = (Button) findViewById(R.id.activity_main_bt_add);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> list, View itemList, int position, long id)
            {
                Contact contact = new Contact();
                contact = (Contact) list.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(mListView);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        loadContactList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo)
    {
        final MenuItem delete = menu.add("Delete");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contact contact = (Contact) mListView.getItemAtPosition(itemInfo.position);
                delete(contact);
                loadContactList();
                return false;
            }
        });
    }

    private void loadContactList()
    {
        ContactDAO dao = new ContactDAO(this);
        List<Contact> contactList = dao.findAll();
        dao.close();

        ContactAdapter adapter = new ContactAdapter(this, contactList);
        mListView.setAdapter(adapter);
    }

    private void delete(Contact contact)
    {
        ContactDAO dao = new ContactDAO(this);
        dao.delete(contact);
        dao.close();
    }
}
