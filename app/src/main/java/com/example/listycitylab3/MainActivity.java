package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.OnFragmentInteractionListener{

    private ArrayList<City> dataList;
    private ListView cityList;
    private ArrayAdapter<City> cityAdapter;

    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        dataList = new ArrayList<>();
        String[] cities = {"Edmonton" , "Vancouver" , "Toronto"};
        String[] provinces = {"AB" , "BC" , "ON" };
        dataList = new ArrayList<City>();
        for (int i = 0 ; i < cities.length ; i++) {
            dataList.add(new City(cities[i] , provinces[i]));
        }

        cityAdapter = new CityArrayAdapter(this , dataList);
        cityList.setAdapter(cityAdapter);


//        String[] cities = {
//                "Edmonton", "Vancouver", "Moscow",
//                "Sydney", "Berlin", "Vienna",
//                "Tokyo", "Beijing", "Osaka", "New Delhi"
//        };

//        dataList.addAll(Arrays.asList(cities));
//        dataList.add(new City("Edmonton" , "AB"));
//        dataList.add(new City("Vancouver" , "BC"));
//        dataList.add(new City("Toronto" , "ON"));
//        dataList.add(new City("Denver" , "CO"));


//        cityList = findViewById(R.id.city_list);
//        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
//        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            AddCityFragment.newInstance(null , -1).show(getSupportFragmentManager() , "Add City");
        });
        cityList.setOnItemClickListener((parent , view , position , id) -> {
            City cityToEdit = dataList.get(position);
            AddCityFragment.newInstance(cityToEdit , position).show(getSupportFragmentManager() , "EDIT_CITY");
        });



    }

    @Override
    public void onOkPressed(City newCity, int position) {
        if (position > -1) {
            // EDIT MODE: Overwrite the existing city at this index
            dataList.set(position, newCity);
        } else {
            // ADD MODE: Just add to the list
            dataList.add(newCity);
        }
        // Refresh the list view
        cityAdapter.notifyDataSetChanged();
    }
}