package com.example.gavri.koveah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by gavri on 3/11/2018.
 */

public class ProjectsAdaptor extends BaseAdapter {

    LayoutInflater mInflator;
    String[] sefarim;
    String[] daf;

    public ProjectsAdaptor(Context c, String[] s, String[] d) {
        sefarim = s;
        daf = d;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sefarim.length;
    }

    @Override
    public Object getItem(int position) {
        return sefarim[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.projects_detail2, null);
        TextView mesechetView = (TextView) v.findViewById(R.id.mesechetView);
        TextView dafView = (TextView) v.findViewById(R.id.dafView);

        String sefer = sefarim[i];
        String myDaf = daf[i];

        mesechetView.setText(sefer);
        dafView.setText(myDaf);


        return v;
    }
}
