package com.example.david.remindmeat.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.david.remindmeat.UpdateRemindMeItemActivity;
import com.example.david.remindmeat.model.RemindItem;
import com.example.david.remindmeat.utils.Constants;

public class ListClickHandler implements AdapterView.OnItemClickListener {
    private Context context;

    public ListClickHandler(Context context){
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RemindItem remindItem = (RemindItem) parent.getItemAtPosition(position);

        Intent remindMeItemUpdateIntent = new Intent(this.context, UpdateRemindMeItemActivity.class);
        remindMeItemUpdateIntent.putExtra(Constants.EXTRA_SELECTED_REMIND_ME_ITEM, remindItem);
        this.context.startActivity(remindMeItemUpdateIntent);
    }
}
