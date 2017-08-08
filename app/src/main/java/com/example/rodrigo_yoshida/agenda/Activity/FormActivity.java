package com.example.rodrigo_yoshida.agenda.Activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rodrigo_yoshida.agenda.DAO.ContactDAO;
import com.example.rodrigo_yoshida.agenda.Model.Contact;
import com.example.rodrigo_yoshida.agenda.R;

import java.io.File;

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

        final Intent intent = getIntent();
        mContact = (Contact) intent.getSerializableExtra("contact");

        if (mContact != null)
            mHelper.editContact(mContact);

        Button buttonPhoto = (Button)findViewById(R.id.activity_bt_camera);
        buttonPhoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String dirPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File filePhoto = new File(dirPhoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto));
                startActivity(intentCamera);
            }
        });
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
            mHelper.name.setError("Campo obrigatorio");
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
