package com.example.rodrigo_yoshida.agenda.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rodrigo_yoshida.agenda.Adapter.SpinnerAdapter;
import com.example.rodrigo_yoshida.agenda.BuildConfig;
import com.example.rodrigo_yoshida.agenda.DAO.ContactDAO;
import com.example.rodrigo_yoshida.agenda.Model.Contact;
import com.example.rodrigo_yoshida.agenda.Model.TypesTelephone;
import com.example.rodrigo_yoshida.agenda.R;

import java.io.File;

public class FormActivity extends AppCompatActivity
{

    Contact mContact;
    FormHelper mHelper;
    String mDirPhoto;
    public static final int CODIGO_CAMERA = 001;

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

        Button buttonPhoto = (Button) findViewById(R.id.activity_bt_camera);
        buttonPhoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mDirPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File filePhoto = new File(mDirPhoto);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                        FormActivity.this, BuildConfig.APPLICATION_ID + ".provider", filePhoto));

                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });

        //Load Spinner
        Spinner spinner = (Spinner) findViewById(R.id.activity_form_type_phone);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, TypesTelephone.getTypes());
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK)
        {
            if (requestCode == CODIGO_CAMERA)
            {
                mHelper.loadPhoto(mDirPhoto);
            }
        }
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
        } else
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

        if (contact.getName().length() <= 0)
            flag = false;

        return flag;
    }
}
