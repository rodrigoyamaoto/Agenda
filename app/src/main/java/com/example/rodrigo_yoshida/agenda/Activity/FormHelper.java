package com.example.rodrigo_yoshida.agenda.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.rodrigo_yoshida.agenda.Model.Contact;
import com.example.rodrigo_yoshida.agenda.R;

/**
 * Created by rodrigo_yoshida on 02/08/2017.
 */

public class FormHelper
{
    Contact contact;

    ImageView photo;
    EditText name;
    EditText organization;
    EditText telephone;
    EditText email;
    EditText address;

    public FormHelper(FormActivity activity)
    {
        contact = new Contact();

        this.photo = (ImageView) activity.findViewById(R.id.activity_form_photo);
        this.name = (EditText) activity.findViewById(R.id.activity_form_name);
        this.organization = (EditText) activity.findViewById(R.id.activity_form_organization);
        this.telephone = (EditText) activity.findViewById(R.id.activity_form_telephone);
        this.email = (EditText) activity.findViewById(R.id.activity_form_email);
        this.address = (EditText) activity.findViewById(R.id.activity_form_address);
    }

    public Contact getContact()
    {
        contact.setPathPhoto((String) photo.getTag());
        contact.setName(name.getText().toString());
        contact.setOrganization(organization.getText().toString());
        contact.setTelephone(telephone.getText().toString());
        contact.setEmail(email.getText().toString());
        contact.setAddress(address.getText().toString());

        return contact;
    }

    public void editContact(Contact contact)
    {
        if(contact.getPathPhoto() != null)
            loadPhoto(contact.getPathPhoto());

        this.name.setText(contact.getName());
        this.organization.setText(contact.getOrganization());
        this.telephone.setText(contact.getTelephone());
        this.email.setText(contact.getEmail());
        this.address.setText(contact.getAddress());
        this.contact = contact;
    }

    public void loadPhoto(String pathPhoto){
        Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
        Bitmap lowerBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
        photo.setImageBitmap(lowerBitmap);
        photo.setScaleType(ImageView.ScaleType.FIT_XY);
        photo.setTag(pathPhoto);
    }
}
