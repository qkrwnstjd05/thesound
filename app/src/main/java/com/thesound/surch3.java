package com.thesound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class surch3 extends AppCompatActivity{
    private ArrayList<listDTO> mArrayList;
    private CustomAdapter3 mAdapter;

    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surch3);

        BottomNavigationView bottomNavigation  = findViewById(R.id.bottomNav);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        MenuItem item = bottomNavigation.getMenu().findItem(R.id.go_to_search3);
        ImageButton searchBtn = (ImageButton)findViewById(R.id.searchBtn);
        ImageButton backBtn = (ImageButton)findViewById(R.id.backBtn);
        EditText seartEdit = (EditText)findViewById(R.id.edit);
        ListView listview = (ListView)findViewById(R.id.listView);
        item.setChecked(true);

        List<String> list = new ArrayList<>();
        List<String> address = new ArrayList<>();

        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adpater);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //String data = (String) adapterView.getItemAtPosition(position);e
                Log.d("text",address.toString());
                Intent i = new Intent(surch3.this,seeanswer3.class);
                i.putExtra("key",address.get(position));
                startActivity(i);
                finish();
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adpater.clear();
                address.clear();
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("petition").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String,Object> jsonData=document.getData();
                                Log.d("key",jsonData.toString());

                                if(jsonData.get("title").toString().contains(seartEdit.getText().toString())) {
                                    list.add(jsonData.get("title").toString());
                                    address.add(document.getId());
                                    adpater.notifyDataSetChanged();
                                }
                            }
                        } else {
                            Log.w("err", task.getException());
                        }
                    }
                });
            }
        });
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("petition").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String,Object> jsonData=document.getData();
                        list.add(jsonData.get("title").toString());
                        address.add(document.getId());
                        adpater.notifyDataSetChanged();
                    }
                } else {
                    Log.w("err", task.getException());
                }
            }
        });

    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.go_to_search3:
                            Intent intent1=new Intent(getApplicationContext(),surch3.class);
                            startActivity(intent1);
                            return true;
                        case R.id.go_to_all_bulletin_board3:
                            Intent intent=new Intent(getApplicationContext(),petitionboard3.class);
                            startActivity(intent);
                            return true;
                        case R.id.go_to_home3:
                            Intent intent2=new Intent(getApplicationContext(),main3.class);
                            startActivity(intent2);
                            return true;
                        case R.id.go_to_suggestionan_swered:
                            Intent intent3=new Intent(getApplicationContext(),our_school_bullentin_board3.class);
                            startActivity(intent3);
                            return true;
                        case R.id.view_authentication_requests:
                            Intent intent4=new Intent(getApplicationContext(),asksee.class);
                            startActivity(intent4);
                            return true;
                    }
                    return false;
                }
            };
}
