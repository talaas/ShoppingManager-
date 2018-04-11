package ecole.eni.fr.shoppingManager.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lnorjoux2016 on 09/04/2018.
 */

public class ListeDeCourse implements Parcelable {

    private int id;
    private float prixTotal;
    private Date dateCourses;
    private String nomListe;
    private List<ItemListe> articles = new ArrayList<ItemListe>();

    public ListeDeCourse(List<ItemListe> articles, Date dateCourses, String nomListe) {
        this.articles = articles;
        this.dateCourses = dateCourses;
        this.nomListe = nomListe;

        this.prixTotal = 0;
        if(articles != null) {
            for (ItemListe a : articles) {
                this.prixTotal += a.getPrixTotal();
            }
        }
    }

    protected ListeDeCourse(Parcel in) {
       // id = in.readInt();
        articles = new ArrayList<ItemListe>();

        dateCourses = (Date) in.readSerializable();
        nomListe = in.readString();
        prixTotal = in.readFloat();
        in.readList(articles, ItemListe.class.getClassLoader());
    }

    public static final Creator<ListeDeCourse> CREATOR = new Creator<ListeDeCourse>() {
        @Override
        public ListeDeCourse createFromParcel(Parcel in) {
            return new ListeDeCourse(in);
        }

        @Override
        public ListeDeCourse[] newArray(int size) {
            return new ListeDeCourse[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemListe> getArticles() {
        return articles;
    }

    public void setArticles(List<ItemListe> articles) {
        this.articles = articles;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDateCourses() {
        return dateCourses;
    }

    public void setDateCourses(Date dateCourses) {
        this.dateCourses = dateCourses;
    }

    public String getNomListe() {
        return nomListe;
    }

    public void setNomListe(String nomListe) {
        this.nomListe = nomListe;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(dateCourses);
        dest.writeString(nomListe);
        dest.writeFloat(prixTotal);
        dest.writeList(articles);
    }
}
