package com.example.gavri.koveah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;



/**
 * Created by gavri on 3/11/2018.
 */

public class ProjectsAdaptor extends BaseAdapter {

    LayoutInflater mInflator;
    List<Project> projects;

    public ProjectsAdaptor(Context c, List<Project> projects) {
        this.projects = projects;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return projects.size();
    }

    @Override
    public Object getItem(int position) {
        return projects.get(position);
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

        String book = projects.get(i).getBookName();
        String page = ((Integer) projects.get(i).getPage()).toString();

        mesechetView.setText(book);
        dafView.setText(page);


        return v;
    }

}
