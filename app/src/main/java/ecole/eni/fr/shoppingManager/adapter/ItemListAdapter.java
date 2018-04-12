package ecole.eni.fr.shoppingManager.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ecole.eni.fr.shoppingManager.R;
import ecole.eni.fr.shoppingManager.dal.DataBaseManager;
import ecole.eni.fr.shoppingManager.beans.ItemListe;

/**
 * Created by stalaa2016 on 09/04/2018.
 */

public class ItemListAdapter extends ArrayAdapter<String> {

    private List<String> nomArticle = new ArrayList<>();
    private List<String> description = new ArrayList<>();
    private List<Integer> qte = new ArrayList<>();
    private List<Float> prix = new ArrayList<>();
    private List<Integer> image = new ArrayList<>();
    private List<Integer> isBuy = new ArrayList<>();
    private Activity context;


    public ItemListAdapter(Activity context, String[] nomArticle, String[] description, Integer[] qte, Float[] prix, Integer[] image, Integer[] isBuy){
        super(context, R.layout.activity_list_main, nomArticle);
        this.nomArticle = Arrays.asList(nomArticle);
        this.description = Arrays.asList(description);
        this.qte = Arrays.asList(qte);
        this.prix = Arrays.asList(prix);
        this.image = Arrays.asList(image);

        this.isBuy = new ArrayList<>();
        for(Integer b : isBuy) {
            this.isBuy.add(b);
        }

        this.context = context;
    }

    public ItemListAdapter(Activity context, List<ItemListe> itemListe, String[] nomArticle){
        super(context, R.layout.activity_list_main, nomArticle);

        for(ItemListe i : itemListe) {
            if(i.getArticle() != null) {
                this.nomArticle.add(i.getArticle().getNom());
                this.description.add(i.getArticle().getDescription());
            }
            this.qte.add(i.getQte());
            this.prix.add(i.getPrixTotal());
            //this.image.add(i.getArticle().getImg());
            this.isBuy.add(i.isInTheCaddie());
        }

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r==null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r  = layoutInflater.inflate(R.layout.activity_list_article, null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();


        }
        viewHolder.image.setImageResource(image.get(position));
        viewHolder.article.setText(nomArticle.get(position));
        viewHolder.description.setText(description.get(position));
        viewHolder.qte.setText(String.valueOf(qte.get(position)));
        viewHolder.prix.setText(String.valueOf(prix.get(position))+"€");
        viewHolder.isBuy.setText(String.valueOf(isBuy.get(position)));
        return r;
    }

    public class ViewHolder{

        public TextView article;
        public TextView description;
        public TextView prix;
        public TextView qte;
        public ImageView image;
        public CheckBox isBuy;

        ViewHolder(View v){
            article = (TextView) v.findViewById(R.id.article);
            description = (TextView) v.findViewById(R.id.description);
            prix = (TextView) v.findViewById(R.id.prix);
            qte = (TextView) v.findViewById(R.id.qte);
            image = (ImageView) v.findViewById(R.id.image);
            isBuy = (CheckBox) v.findViewById(R.id.isBuy);
        }
    }

}
