package com.example.plasmabank1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.plasmabank1.Adapters.SearchAdapter;
import com.example.plasmabank1.DataModels.Donor;
import com.example.plasmabank1.R;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {
    List<Donor> donorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        donorList = new ArrayList<>();
        String json;
        String city, blood_group;
        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        city = intent.getStringExtra("city");
        blood_group = intent.getStringExtra("blood_group");
        TextView heading = findViewById(R.id.heading);
        String str = "Donors in " + city + " with blood group " + blood_group;
        heading.setText(str);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Donor>>() {
        }.getType();
        List<Donor> dataModels = gson.fromJson(json, type);
        if (dataModels != null && dataModels.isEmpty()) {
            heading.setText("No results");
        }else if(dataModels!=null){
            donorList.addAll(dataModels);
        }
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SearchAdapter adapter = new SearchAdapter(donorList, SearchResults.this);
        recyclerView.setAdapter(adapter);



    }


}