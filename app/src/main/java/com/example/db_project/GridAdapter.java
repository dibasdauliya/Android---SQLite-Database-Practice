package com.example.db_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<GridOption> options;

    public GridAdapter(Context context, List<GridOption> options) {
        this.context = context;
        this.options = options;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int position) {
        return options.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView icon = convertView.findViewById(R.id.icon);
        TextView text = convertView.findViewById(R.id.text);

        GridOption option = options.get(position);
        icon.setImageResource(option.getIcon());
        text.setText(option.getText());

        return convertView;
    }
}
