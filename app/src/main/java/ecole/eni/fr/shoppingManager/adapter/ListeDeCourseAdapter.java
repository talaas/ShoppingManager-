package ecole.eni.fr.shoppingManager.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ecole.eni.fr.shoppingManager.R;
import ecole.eni.fr.shoppingManager.beans.ListeDeCourse;

/**
 * Created by lnorjoux2016 on 09/04/2018.
 */

public class ListeDeCourseAdapter extends RecyclerView.Adapter<ListeDeCourseAdapter.MyViewHolder> {

    private final List<ListeDeCourse> listesDeCourse;
    private final Context context;
    //private final int ressourceId;



    public ListeDeCourseAdapter(@NonNull Context context/*, int resource*/, @NonNull List<ListeDeCourse>  listesDeCourse) {

        this.listesDeCourse = listesDeCourse;
        this.context = context;
      //  this.ressourceId = resource;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListeDeCourse movie = listesDeCourse.get(position);
        holder.nom.setText(movie.getNomListe());
        //holder.description.setText(movie.getDateCourses().toString());
        holder.prix.setText(String.valueOf(movie.getPrixTotal()) + " €");
    }

    @Override
    public int getItemCount() {
        return listesDeCourse.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom, description, prix;

        public MyViewHolder(View view) {
            super(view);
            nom = (TextView) view.findViewById(R.id.nom);
            description = (TextView) view.findViewById(R.id.description);
            prix = (TextView) view.findViewById(R.id.prix);
        }
    }
}
