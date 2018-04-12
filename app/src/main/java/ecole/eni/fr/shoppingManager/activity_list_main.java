package ecole.eni.fr.shoppingManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import ecole.eni.fr.shoppingManager.R;
import ecole.eni.fr.shoppingManager.adapter.ItemListAdapter;
import ecole.eni.fr.shoppingManager.beans.ItemDB;
import ecole.eni.fr.shoppingManager.beans.ItemListe;
import ecole.eni.fr.shoppingManager.dal.DataBaseManager;
import ecole.eni.fr.shoppingManager.beans.ItemListe;
import ecole.eni.fr.shoppingManager.beans.ListeDeCourse;

public class activity_list_main extends AppCompatActivity {
    ListView lst;
    String[] article = {"Fraise", "Banane"};
    String[] description = {"C'est une fraise", "C'est une banane"};
    Integer[] image = {R.drawable.bananes, R.drawable.fraise};
    Float[] prix = {300f, 200f};
    boolean[] isBuy = {true, false};
    Integer[] qte = {1, 2};

    private TextView listeCourseView;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.deleteAll();
        dataBaseManager.insertData("Banane", "Ceci est une Banane", 2131165283, 1.1f, 1, 0);
        List<ItemListe> courses = dataBaseManager.readAll();

        lst = findViewById(R.id.lst);
        Intent intent = this.getIntent();
       // Bundle test = intent.getExtras();
       // ListeDeCourse listSel = intent.getExtras().getParcelable("listeSel");
       // int index = intent.getExtras().getInt("nbArticle");

        String[] nomArticles = new String[courses.size()];
        int idx = 0;
        for(ItemListe i : courses) {
            nomArticles[idx] = i.getArticle().getNom();
            idx++;
        }

        ItemListAdapter adapter = new ItemListAdapter(this, courses, nomArticles);
        lst.setAdapter(adapter);

/*
        //-----------------------Sammy
        //listeCourseView = (TextView) findViewById(R.id.article);


       // dataBaseManager.insertData("Fraise", "Ceci est une Fraise", 1, 1, 1, 0);


        for (ItemListe course : courses) {
            listeCourseView.append(course.getArticle().getNom().toString());
            //listeCourseView.append(course.toString() + "\n\n");
        }
        dataBaseManager.close();*/
    }

        /*lst = findViewById(R.id.listView);
        ItemListAdapter itemListAdapter = new ItemListAdapter(this, article, description, qte, prix, image, isBuy);
        lst.setAdapter(itemListAdapter);/*/

}


