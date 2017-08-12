package com.example.rodrigo_yoshida.agenda.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter
{
    private Context context;
    private List<String> listOfTypes = new ArrayList<>();

    public SpinnerAdapter(Context context, String[] arrayOfTypes)
    {
        this.context = context;

        for (int i = 0; i < arrayOfTypes.length; i++)
            this.listOfTypes.add(i, arrayOfTypes[i]);
    }

    @Override
    public int getCount()
    {
        return this.listOfTypes.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.listOfTypes.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView textView = new TextView(context);
        textView.setText(listOfTypes.get(position));

        return textView;
    }
}
