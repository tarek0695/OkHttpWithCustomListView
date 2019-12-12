package com.example.okhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Model> model = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<Model> model) {
        this.context = context;
        this.model = model;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return model.size();
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

        convertView = inflater.inflate(R.layout.custom_list_layout, null);

        TextView idtv = convertView.findViewById(R.id.idtv);
        TextView nameTv = convertView.findViewById(R.id.nametv);
        TextView typeTv = convertView.findViewById(R.id.typetv);
        TextView colorTv = convertView.findViewById(R.id.colortv);
        ImageView imgTv = convertView.findViewById(R.id.imgv);

        idtv.setText(String.valueOf(model.get(position).getId()));
        nameTv.setText(model.get(position).getName());
        typeTv.setText(model.get(position).getTypes());
        colorTv.setText(model.get(position).getColorofeyes());
        Picasso.get().load(model.get(position).getImage()).into(imgTv);

        return convertView;
    }
}
