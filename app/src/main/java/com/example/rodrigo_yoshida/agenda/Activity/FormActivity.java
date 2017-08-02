package com.example.rodrigo_yoshida.agenda.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rodrigo_yoshida.agenda.DAO.ContactDAO;
import com.example.rodrigo_yoshida.agenda.Model.Contact;
import com.example.rodrigo_yoshida.agenda.R;

public class FormActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.menu_form_save:
                FormHelper helper = new FormHelper(this);
                ContactDAO dao = new ContactDAO(this);
                Contact contact = helper.getContact();
                dao.insert(contact);
                dao.close();

                Toast.makeText(this, "Contato salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
