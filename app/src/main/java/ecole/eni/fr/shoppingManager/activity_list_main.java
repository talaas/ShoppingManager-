package ecole.eni.fr.shoppingManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ecole.eni.fr.shoppingManager.R;
import ecole.eni.fr.shoppingManager.adapter.ItemListAdapter;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        lst = findViewById(R.id.lst);
        Intent intent = this.getIntent();
        Bundle test = intent.getExtras();
        ListeDeCourse listSel = intent.getExtras().getParcelable("listeSel");
        int index = intent.getExtras().getInt("nbArticle");

        String[] nomArticles = new String[listSel.getArticles().size()];
        int idx = 0;
        for(ItemListe i : listSel.getArticles()) {
            nomArticles[idx] = i.getArticle().getNom();
            idx++;
        }

        ItemListAdapter adapter = new ItemListAdapter(this, listSel.getArticles(), nomArticles);
        lst.setAdapter(adapter);

    }
}
