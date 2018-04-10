package com.example.lnorjoux2016.shoppingmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import ecole.eni.fr.shoppingManager.R;
import ecole.eni.fr.shoppingManager.adapter.ItemListAdapter;

public class activity_list_main extends AppCompatActivity {
    ListView lst;
    String[] article = {"Fraise", "Banane"};
    String[] description = {"C'est une fraise", "C'est une banane"};
    Integer[] image = {R.drawable.bananes, R.drawable.fraise};
    Integer[] prix = {300, 200};
    boolean[] isBuy = {true, false};
    Integer[] qte = {1, 2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        lst = findViewById(R.id.listView);
        ItemListAdapter itemListAdapter = new ItemListAdapter(this, article, description, qte, prix, image, isBuy);
        lst.setAdapter(itemListAdapter);
    }
}
