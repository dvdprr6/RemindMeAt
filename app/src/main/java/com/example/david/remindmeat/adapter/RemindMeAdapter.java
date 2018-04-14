package com.example.david.remindmeat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.david.remindmeat.R;
import com.example.david.remindmeat.model.RemindItem;

import java.util.List;

public class RemindMeAdapter extends ArrayAdapter<RemindItem>{

    public RemindMeAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<RemindItem> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = View.inflate(getContext(), R.layout.layout_list_text_view, null);
        }

        StringBuffer sb = new StringBuffer();

        RemindItem remindMeItem = getItem(position);

        String title = remindMeItem.getTitle();
        String description = remindMeItem.getDescription();

        sb.append("Title: " + title + "\n" + "Description: " + description);

        TextView text = convertView.findViewById(R.id.listTextView);
        text.setText(sb.toString());

        return convertView;
    }
}
