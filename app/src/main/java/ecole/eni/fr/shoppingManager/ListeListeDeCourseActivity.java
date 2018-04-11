package ecole.eni.fr.shoppingManager;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ecole.eni.fr.shoppingManager.adapter.ListeDeCourseAdapter;
import ecole.eni.fr.shoppingManager.beans.ArticleRef;
import ecole.eni.fr.shoppingManager.beans.ItemListe;
import ecole.eni.fr.shoppingManager.beans.ListeDeCourse;

public class ListeListeDeCourseActivity extends AppCompatActivity {

    private List<ListeDeCourse> listeListeDeCourse = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListeDeCourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_liste_liste_de_course);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    mAdapter = new ListeDeCourseAdapter(this, listeListeDeCourse);

    recyclerView.setHasFixedSize(true);

    // vertical RecyclerView
    // keep list_list_rowxml width to `match_parent`
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

    // horizontal RecyclerView
    // keep list_list_rowxml width to `wrap_content`
//         RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

    recyclerView.setLayoutManager(mLayoutManager);

    // adding inbuilt divider line
    recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

    // adding custom divider line with padding 16dp
    // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    recyclerView.setAdapter(mAdapter);

    // row click listener
    recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
        @Override
        public void onClick(View view, int position) {
            ListeDeCourse listeSel = listeListeDeCourse.get(position);

            Intent intent = new Intent(ListeListeDeCourseActivity.this, activity_list_main.class);
            intent.putExtra("nbArticle", listeSel.getArticles().size());
            intent.putExtra("listeSel", listeSel);

            startActivity(intent);
        }

        @Override
        public void onLongClick(View view, int position) {

        }
    }));

    prepareArticleData();
}

    public void onClickAjouterButton(View view) {
        Intent intent = new Intent(ListeListeDeCourseActivity.this, CreateOrEditActivity.class);
        startActivity(intent);
    }

    private void prepareArticleData() {

        ArticleRef article1 = new ArticleRef("NOMARTICLE1", 1.11f, "LADESCRIPTION", "img");
        ArticleRef article2 = new ArticleRef("NOMARTICLE2", 2.22f, "LADESCRIPTION", "img");
        ArticleRef article3 = new ArticleRef("NOMARTICLE3", 3.33f, "LADESCRIPTION", "img");
        ArticleRef article4 = new ArticleRef("NOMARTICLE4", 4.44f, "LADESCRIPTION", "img");
        ArticleRef article5 = new ArticleRef("NOMARTICLE5", 5.55f, "LADESCRIPTION", "img");
        ArticleRef article6 = new ArticleRef("NOMARTICLE6", 6.66f, "LADESCRIPTION", "img");
        ArticleRef article7 = new ArticleRef("NOMARTICLE7", 7.77f, "LADESCRIPTION", "img");
        ArticleRef article8 = new ArticleRef("NOMARTICLE8", 8.88f, "LADESCRIPTION", "img");
        ArticleRef article9 = new ArticleRef("NOMARTICLE9", 9.99f, "LADESCRIPTION", "img");

        ItemListe item1 = new ItemListe(1, false, article1);
        ItemListe item2 = new ItemListe(1, false, article2);
        ItemListe item3 = new ItemListe(1, false, article3);
        ItemListe item4 = new ItemListe(1, false, article4);
        ItemListe item5 = new ItemListe(1, false, article5);
        ItemListe item6 = new ItemListe(1, false, article6);
        ItemListe item7 = new ItemListe(1, false, article7);
        ItemListe item8 = new ItemListe(1, false, article8);
        ItemListe item9 = new ItemListe(1, false, article9);

        List<ItemListe> itemsListe1 = new ArrayList<>();
        List<ItemListe> itemsListe2 = new ArrayList<>();
        List<ItemListe> itemsListe3 = new ArrayList<>();
        List<ItemListe> itemsListe4 = new ArrayList<>();

        itemsListe1.add(item1);
        itemsListe1.add(item2);
        itemsListe1.add(item3);

        itemsListe2.add(item1);
        itemsListe2.add(item4);
        itemsListe2.add(item5);

        itemsListe3.add(item5);
        itemsListe3.add(item6);
        itemsListe3.add(item7);

        itemsListe4.add(item6);
        itemsListe4.add(item8);
        itemsListe4.add(item9);

        // 6.66
        ListeDeCourse liste1 = new ListeDeCourse(itemsListe1, new Date(), "LENOM1");
        listeListeDeCourse.add(liste1);

        // 11.1
        ListeDeCourse liste2 = new ListeDeCourse(itemsListe2, new Date(), "LENOM2");
        listeListeDeCourse.add(liste2);

        // 19.98
        ListeDeCourse liste3 = new ListeDeCourse(itemsListe3, new Date(), "LENOM3");
        listeListeDeCourse.add(liste3);

        // 25.53 TODO arrondi
        ListeDeCourse liste4 = new ListeDeCourse(itemsListe4, new Date(), "LENOM4");
        listeListeDeCourse.add(liste4);

        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }
}
