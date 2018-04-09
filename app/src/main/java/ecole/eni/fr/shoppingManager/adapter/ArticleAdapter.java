package ecole.eni.fr.shoppingManager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import ecole.eni.fr.shoppingManager.R;
import ecole.eni.fr.shoppingManager.beans.ArticleRef;


/**
 * Created by pbontempi2017 on 13/06/2017.
 */
public class ArticleAdapter extends ArrayAdapter<ArticleRef> {

    private final List<ArticleRef> articles;
    private final Context context;
    private final int ressourceId;

    public ArticleAdapter(Context context, int resource,List<ArticleRef> articles) {
        super(context, resource, articles);
        this.articles = articles;
        this.context = context;
        this.ressourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){

            holder = new ViewHolder();

            convertView = inflater.inflate(ressourceId, parent, false);

            holder.nom = (TextView) convertView.findViewById(R.id.ListArticleName);
            holder.prix = (TextView) convertView.findViewById(R.id.ListArticlePrice);
            holder.description = (TextView) convertView.findViewById(R.id.ListArticleDescription);
            holder.img = (TextView) convertView.findViewById(R.id.ListArticleImg);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        ArticleRef article = articles.get(position);

        holder.nom.setText(article.getNom());
        holder.prix.setText(article.getPrix().toString() + " €");
        holder.description.setText(article.getDescription().toString() + " €");
        holder.img.setText(article.getImg().toString() + " €");

        return convertView;
    }

    /**
     * Class static permet de mettre en cache les widgets
     */
    static class ViewHolder{
        TextView nom;
        TextView prix;
        TextView description;
        TextView img;
    }
}
