package com.example.note;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<MessageInfo> {
    private Context mContext;
    private  int mResource;

    public MessageAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MessageInfo> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mResource=resource;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);

        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView text=convertView.findViewById((R.id.text));

        text.setText(getItem(position).getText());
        return  convertView;
    }
}
