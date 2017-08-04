package com.example.rodrigo_yoshida.agenda.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.Manifest;

import com.example.rodrigo_yoshida.agenda.Adapter.ContactAdapter;
import com.example.rodrigo_yoshida.agenda.DAO.ContactDAO;
import com.example.rodrigo_yoshida.agenda.Model.Contact;
import com.example.rodrigo_yoshida.agenda.R;

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
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Contact contact = (Contact) mListView.getItemAtPosition(itemInfo.position);

        //Menus de contexto para fazer chamadas
        MenuItem call = menu.add("Ligar");
        call.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
                {
                    Intent call = new Intent(Intent.ACTION_VIEW);
                    call.setData(Uri.parse("tel:" + contact.getTelephone()));
                    startActivity(call);
                }
                else
                {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }
                return false;
            }
        });

        //Menu de contexto para Geo Localização
        MenuItem geoLocation = menu.add("Abrir Mapa");
        Intent intentGeoLocation = new Intent(Intent.ACTION_VIEW);
        intentGeoLocation.setData(Uri.parse("geo:0,0?q=" + contact.getAddress()));
        geoLocation.setIntent(intentGeoLocation);

        //Menu de contexto para deletar
        MenuItem delete = menu.add("Delete");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
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
