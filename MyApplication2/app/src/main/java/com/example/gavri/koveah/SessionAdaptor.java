package com.example.gavri.koveah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by gavri on 3/11/2018.
 */

public class SessionAdaptor extends BaseAdapter {

    LayoutInflater mInflator;
    String sefer;
    String daf;
    String session;
    String summary;

    public SessionAdaptor(Context c, String sef, String d, String sess, String summ){
        sefer = sef;
        daf = d;
        session = sess;
        summary = summ;

        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.activity_detail, null);
        TextView projectView = (TextView) v.findViewById(R.id.projectView);
        TextView sessionView = (TextView) v.findViewById(R.id.sessionView);
        TextView makomView = (TextView) v.findViewById(R.id.makomView);
        TextView summaryView = (TextView) v.findViewById(R.id.summaryView);


        return null;
    }
}
