package ecole.eni.fr.shoppingManager.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lnorjoux2016 on 09/04/2018.
 */

public class ItemListe implements Parcelable {

    private int id;
    private int qte;
    private boolean isInTheCaddie;
    private float prixTotal;
    private ArticleRef article;

    public ItemListe(int qte, boolean isInTheCaddie, ArticleRef article) {
        this.qte = qte;
        this.isInTheCaddie = isInTheCaddie;
        this.prixTotal = article.getPrix() * qte;
        this.article = article;
    }

    protected ItemListe(Parcel in) {

        isInTheCaddie = (Boolean) in.readValue(null);
        prixTotal = in.readFloat();
        article = in.readParcelable(ArticleRef.class.getClassLoader());
    }

    public static final Creator<ItemListe> CREATOR = new Creator<ItemListe>() {
        @Override
        public ItemListe createFromParcel(Parcel in) {
            return new ItemListe(in);
        }

        @Override
        public ItemListe[] newArray(int size) {
            return new ItemListe[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public boolean isInTheCaddie() {
        return isInTheCaddie;
    }

    public void setInTheCaddie(boolean inTheCaddie) {
        isInTheCaddie = inTheCaddie;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public ArticleRef getArticle() {
        return article;
    }

    public void setArticle(ArticleRef article) {
        this.article = article;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isInTheCaddie);
        dest.writeFloat(prixTotal);
        dest.writeParcelable(article, flags);
    }
}
