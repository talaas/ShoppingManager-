package ecole.eni.fr.shoppingManager.beans;

/**
 * Created by lnorjoux2016 on 09/04/2018.
 */

public class ItemListe {

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
}
