package com.example.rodrigo_yoshida.agenda.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodrigo_yoshida.agenda.Activity.FormHelper;
import com.example.rodrigo_yoshida.agenda.Model.Contact;
import com.example.rodrigo_yoshida.agenda.R;

import java.util.List;
import java.util.Objects;

/**
 * Created by rodrigo_yoshida on 01/08/2017.
 */

public class ContactAdapter extends BaseAdapter
{
    private final Context context;
    private final List<Contact> contactList;

    public ContactAdapter(Context context, List<Contact> contactList)
    {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount()
    {
        return this.contactList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.contactList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Contact contact = this.contactList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_contact, parent, false);

        ImageView photo = (ImageView) view.findViewById(R.id.item_contact_photo);
        TextView name = (TextView) view.findViewById(R.id.item_contact_name);
        TextView telephone = (TextView) view.findViewById((R.id.item_contact_telephone));

        Bitmap bitmap = BitmapFactory.decodeFile(contactList.get(position).getPathPhoto());
        Bitmap lowerBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
        photo.setImageBitmap(lowerBitmap);
        photo.setScaleType(ImageView.ScaleType.FIT_XY);

        name.setText(contact.getName());
        telephone.setText(contact.getTelephone());

        return view;
    }
}
