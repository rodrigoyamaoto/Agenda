package com.example.rodrigo_yoshida.agenda.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = inflater.inflate(R.layout.item_contact, parent, false);

        TextView name = (TextView) view.findViewById(R.id.item_contact_name);

        name.setText(contact.getId() + " - " + contact.getName());

        return view;
    }
}
