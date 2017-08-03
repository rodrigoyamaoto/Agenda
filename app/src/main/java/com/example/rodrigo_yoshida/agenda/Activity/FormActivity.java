package com.example.rodrigo_yoshida.agenda.Activity;

import android.content.Intent;
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

    Contact mContact;
    FormHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        this.mContact = new Contact();
        this.mHelper = new FormHelper(this);

        Intent intent = getIntent();
        mContact = (Contact) intent.getSerializableExtra("contact");

        if (mContact != null)
            mHelper.editContact(mContact);
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
        switch (item.getItemId())
        {
            case R.id.menu_form_save:

                if (mContact == null || mContact.getId() == null)
                    insert();
                else
                    update();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insert()
    {
        mContact = mHelper.getContact();

        if (doValidate(mContact))
        {
            ContactDAO dao = new ContactDAO(this);
            dao.insert(mContact);
            dao.close();

            Toast.makeText(this, mContact.getName() + " salvo com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
            Toast.makeText(this, "Campo nome é obrigatorio", Toast.LENGTH_SHORT).show();
    }

    public void update()
    {
        mContact = mHelper.getContact();
        ContactDAO dao = new ContactDAO(this);
        dao.update(mContact);
        dao.close();

        Toast.makeText(this, "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

    public boolean doValidate(Contact contact)
    {
        boolean flag = true;

        if(contact.getName().length() <= 0)
            flag = false;

        return flag;
    }
}
