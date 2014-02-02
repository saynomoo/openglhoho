package com.example.openglhoho;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class AdapterAdapter<T> extends BaseAdapter{
    protected List<T> entries;

    public AdapterAdapter() {
        this(new ArrayList<T>());
    }
    public AdapterAdapter(List<T> entries) {
        this.entries = entries;
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
