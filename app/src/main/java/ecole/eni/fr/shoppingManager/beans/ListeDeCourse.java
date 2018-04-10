package ecole.eni.fr.shoppingManager.beans;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lnorjoux2016 on 09/04/2018.
 */

public class ListeDeCourse {

    private int id;
    private List<ItemListe> articles = new ArrayList<ItemListe>();
    private float prixTotal;
    private Date dateCourses;
    private String nomListe;

    public ListeDeCourse(List<ItemListe> articles, Date dateCourses, String nomListe) {
        this.articles = articles;
        this.dateCourses = dateCourses;
        this.nomListe = nomListe;

        this.prixTotal = 0;
        for(ItemListe a : articles) {
            this.prixTotal += a.getPrixTotal();
        }
    }

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
}
