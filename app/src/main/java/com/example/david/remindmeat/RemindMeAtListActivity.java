package com.example.david.remindmeat;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RemindMeAtListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_me_at_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.EmptyContainerInSingleViewOfRemindMeAtListActivity, new PlaceholderFragment())
                    .commit();
        }
    }

    public void addItem(View view){

    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            String[] forecastArray = {
                    "Today-Sunny 50/60",
                    "Tomorrow-Cloudy 20/30",
                    "Wednesday-Snowy 40/50",
                    "Thursday-Rainy 20/40",
                    "Friday-Funny 20/50",
                    "Sat-Sunny 70/80",
                    "Sun-Sunny 90/100",
                    "Rick",
                    "Morty",
                    "Summer"
            };

            List<String> weekForecast = new LinkedList(Arrays.asList(forecastArray));

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.layout_list_text_view, R.id.listTextView, weekForecast);

            View rootView = inflater.inflate(R.layout.list_view_fragment, container, false);

            ListView listView = rootView.findViewById(R.id.RemindMeAtlistview);

            listView.setAdapter(adapter);

            return rootView;

        }
    }

}
