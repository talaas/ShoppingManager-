package ecole.eni.fr.shoppingManager;

import android.graphics.Movie;
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
import ecole.eni.fr.shoppingManager.beans.ListeDeCourse;

public class ListeListeDeCourseActivity extends AppCompatActivity {

    private List<ListeDeCourse> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListeDeCourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_liste_liste_de_course);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    mAdapter = new ListeDeCourseAdapter(this, movieList);

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
            ListeDeCourse movie = movieList.get(position);
            Toast.makeText(getApplicationContext(), movie.getNomListe() + " is selected!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongClick(View view, int position) {

        }
    }));

    prepareMovieData();
}

        /**
         * Prepares sample data to provide data set to adapter
         */
    private void prepareMovieData() {

        ListeDeCourse liste1 = new ListeDeCourse(12.2f, new Date(), "LENOM1");
        movieList.add(liste1);
        ListeDeCourse liste2 = new ListeDeCourse(12.2f, new Date(), "LENOM2");
        movieList.add(liste2);
        ListeDeCourse liste3 = new ListeDeCourse(12.2f, new Date(), "LENOM3");
        movieList.add(liste3);
        ListeDeCourse liste4 = new ListeDeCourse(12.2f, new Date(), "LENOM4");
        movieList.add(liste4);
        ListeDeCourse liste5 = new ListeDeCourse(12.2f, new Date(), "LENOM5");
        movieList.add(liste5);
        ListeDeCourse liste6 = new ListeDeCourse(12.2f, new Date(), "LENOM6");
        movieList.add(liste6);
        ListeDeCourse liste7 = new ListeDeCourse(12.2f, new Date(), "LENOM7");
        movieList.add(liste7);
        ListeDeCourse liste8 = new ListeDeCourse(12.2f, new Date(), "LENOM8");
        movieList.add(liste8);
        ListeDeCourse liste9 = new ListeDeCourse(12.2f, new Date(), "LENOM9");
        movieList.add(liste9);

        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }
}
