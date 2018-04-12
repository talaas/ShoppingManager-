package ecole.eni.fr.shoppingManager.beans;

import java.util.Date;

/**
 * Created by stalaa2016 on 12/04/2018.
 */

public class ItemDB {



        private int idListe;
        private String article;
        private  String description;
        private int image;
        private int prix;
        private int qte;
        private int isBuy;

    public ItemDB(int idListe, String article, String description, int image, int prix, int qte, int isBuy) {
        this.idListe = idListe;
        this.article = article;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.qte = qte;
        this.isBuy = isBuy;
    }


    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }


    @Override
    public String toString() {
        return "ItemDB{" +
                "idListe=" + idListe +
                ", article='" + article + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", prix=" + prix +
                ", qte=" + qte +
                ", isBuy=" + isBuy +
                '}';
    }
}
