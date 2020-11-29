package com.example.internlogin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.internlogin.Model.News;
import com.example.internlogin.R;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    List<News> list;
    Context context;

    public NewsAdapter(List<News> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View layout = LayoutInflater.from(context).inflate(R.layout.layout_news_row, viewGroup, false );
        TextView title = (TextView) layout.findViewById(R.id.txt_title);
        title.setText(list.get(i).getTitle());

        return layout;
    }
}
