package ecole.eni.fr.shoppingManager.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import ecole.eni.fr.shoppingManager.beans.ArticleRef;
import ecole.eni.fr.shoppingManager.provider.ArticleRefHelper;


public class ArticleRefDAL {

    private Context context;

    public ArticleRefDAL(Context context) {
        this.context = context;
    }

    public void addArticle(ArticleRef article) {
        context.getContentResolver().insert( ArticleRefHelper.CONTENT_URI  , constructArticleDB(article));
    }

    private ContentValues constructArticleDB(ArticleRef article) {
        ContentValues values = new ContentValues();
        values.put("NOM", article.getNom());
        values.put("DESCRIPTION", article.getDescription());
        values.put("PRIX", article.getPrix());
        values.put("IMG", article.getImg());
        return values;
    }

    /**
     *
     * @return List
     */
    public List<ArticleRef> getArticles() {

        String[] columns = ArticleRefHelper.getColumns();

        Cursor cursor = context.getContentResolver().query(ArticleRefHelper.CONTENT_URI,
                                                columns, null,
                                                null, null);

        List<ArticleRef> articles = new ArrayList<ArticleRef>() ;

        if(cursor != null && cursor.moveToFirst()) {

            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("_ID"));
                String nom = cursor.getString(cursor.getColumnIndex("NOM"));
                Float prix = cursor.getFloat(cursor.getColumnIndex("PRIX"));
                String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                String img = cursor.getString(cursor.getColumnIndex("IMG"));
                articles.add(new ArticleRef(nom, prix, description, img));
            }while (cursor.moveToNext());

            cursor.close();
        }

        return articles;
    }

    /**
     *
     * @param id
     * @return
     */
    public ArticleRef getArticle(Integer id) {
        String[] columns = ArticleRefHelper.getColumns();
        Cursor cursor = context.getContentResolver().query(
                ArticleRefHelper.CONTENT_URI, columns,
                "_ID=?"
                , new String[]{String.valueOf(id)}, null);
        ArticleRef article = null;
        if (cursor != null && cursor.moveToFirst()) {
            article = new ArticleRef(cursor.getInt(cursor.getColumnIndex("_ID")),
                    cursor.getString(cursor.getColumnIndex("NOM")),
                    cursor.getFloat(cursor.getColumnIndex("PRIX")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPTION")),
                    cursor.getString(cursor.getColumnIndex("IMG")));

            cursor.close();
        }


        return article;

    }

    /**
     *
     * @param articleId
     * @param article
     */
    public void setArticle(Integer articleId, ArticleRef article) {

        context.getContentResolver().update(ArticleRefHelper.CONTENT_URI, constructArticleDB(article),
                "_ID=?"
                , new String[]{String.valueOf(articleId)});

    }

    /**
     *
     * @param bought
     * @return
     */
    private Integer convertBoolean(Boolean bought) {
        if (bought == true)
            return 1;
        else
            return 0;
    }

    /**
     *
     * @param articleId
     */
    public void deleteArticle(int articleId) {
        context.getContentResolver().delete(ArticleRefHelper.CONTENT_URI,"_ID=?"
                , new String[]{String.valueOf(articleId)});
    }

    private long getId(Uri uri){
        String last = uri.getLastPathSegment();
        if(last != null){
            try {
                return Long.parseLong(last);
            }
            catch (NumberFormatException e){

            }
        }
        return -1;
    }

}
