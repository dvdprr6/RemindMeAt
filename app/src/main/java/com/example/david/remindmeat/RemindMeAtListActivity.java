package com.example.david.remindmeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RemindMeAtListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_me_at_list);

        String[] students = {"obi", "mirna", "david", "cristal", "haythem"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.layout_list_text_view, students);

        listView = findViewById(R.id.myListView);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                TextView textView = view.findViewById(R.id.listTextView);
                Toast.makeText(RemindMeAtListActivity.this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
