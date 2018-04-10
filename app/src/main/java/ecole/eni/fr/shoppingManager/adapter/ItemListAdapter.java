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


import java.util.List;

import ecole.eni.fr.shoppingManager.R;

/**
 * Created by stalaa2016 on 09/04/2018.
 */

public class ItemListAdapter extends ArrayAdapter<String> {

    private String[] nomArticle;
    private String[] description;
    private Integer[] qte;
    private Integer[] prix;
    private Integer[] image;
    private boolean[] isBuy;
    private Activity context;


public ItemListAdapter(Activity context, String[] nomArticle, String[] description, Integer[] qte, Integer[] prix, Integer[] image, boolean[] isBuy){
    super(context, R.layout.activity_list_main, nomArticle);
    this.nomArticle = nomArticle;
    this.description = description;
    this.qte = qte;
    this.prix = prix;
    this.image = image;
    this.isBuy = isBuy;
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
        viewHolder.image.setImageResource(image[position]);
        viewHolder.article.setText(nomArticle[position]);
        viewHolder.description.setText(description[position]);
        viewHolder.qte.setText(String.valueOf(qte[position]));
        viewHolder.prix.setText(String.valueOf(prix[position])+"€");




        viewHolder.isBuy.setChecked(isBuy[position]);

        return r;


    }

    class ViewHolder{

        TextView article;
        TextView description;
        TextView prix;
        TextView qte;
        ImageView image;
        CheckBox isBuy;
        ViewHolder(View v){
            article=v.findViewById(R.id.article);
            description=v.findViewById(R.id.description);
            prix=v.findViewById(R.id.prix);
            qte=v.findViewById(R.id.qte);
            image=v.findViewById(R.id.image);
            isBuy=v.findViewById(R.id.isBuy);
        }
    }

}
