package com.example.david.remindmeat;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.david.remindmeat.adapter.RemindMeAdapter;
import com.example.david.remindmeat.dao.RemindDao;
import com.example.david.remindmeat.dao.RemindItemDao;
import com.example.david.remindmeat.global.SharedObject;
import com.example.david.remindmeat.model.Item;
import com.example.david.remindmeat.model.RemindItem;
import com.example.david.remindmeat.model.UserItem;
import com.example.david.remindmeat.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class RemindMeAtListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_me_at_list);

        PlaceholderFragment placeholderFragment = new PlaceholderFragment();

        UserItem userItem = SharedObject.getInstance().getSharedUserItemObject();
        RemindDao remindItemDao = new RemindItemDao(this);

        List<Item> remindMeItems = remindItemDao.findRemindItemsByUserId(userItem.getId());

        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.REMIND_ME_ITEMS_ARRAY, (ArrayList<Item>)remindMeItems);
        placeholderFragment.setArguments(args);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.RemindItemListFrame, placeholderFragment)
                    .commit();
        }
    }

    public void addItem(View view){
        Intent creatRemindItemIntent = new Intent(this, CreateRemindItem.class);
        startActivity(creatRemindItemIntent);
    }

    public void mainMenu(View view){
        Intent mainMenuIntent = new Intent(this, MainMenuActivity.class);
        startActivity(mainMenuIntent);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            List<RemindItem> remindMeItems = getArguments().getParcelableArrayList(Constants.REMIND_ME_ITEMS_ARRAY);

            ArrayAdapter<RemindItem> adapter = new RemindMeAdapter(getActivity(), R.layout.layout_list_text_view, R.id.listTextView, remindMeItems);

            View rootView = inflater.inflate(R.layout.list_view_fragment, container, false);

            ListView listView = rootView.findViewById(R.id.RemindMeAtlistview);

            listView.setAdapter(adapter);

            return rootView;

        }
    }

}
