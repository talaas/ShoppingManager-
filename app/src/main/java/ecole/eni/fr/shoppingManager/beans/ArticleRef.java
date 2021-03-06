package ecole.eni.fr.shoppingManager.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ArticleRef implements Parcelable {

    private Integer id;
    private String nom;
    private Float prix;
    private String description;
    private Integer img;

    public ArticleRef() {
    }

    public ArticleRef(String nom, Float prix, String description, Integer img) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.img = img;
    }

    public ArticleRef(Integer id, String nom, Float prix, String description, Integer img) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.img = img;
    }

    protected ArticleRef(Parcel in) {
        nom = in.readString();
        prix = in.readFloat();
        description = in.readString();
        img = in.readInt();
    }

    public static final Creator<ArticleRef> CREATOR = new Creator<ArticleRef>() {
        @Override
        public ArticleRef createFromParcel(Parcel in) {
            return new ArticleRef(in);
        }

        @Override
        public ArticleRef[] newArray(int size) {
            return new ArticleRef[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeFloat(prix);
        dest.writeString(description);
        dest.writeInt(img);
    }


}
