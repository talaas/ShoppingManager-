package ecole.eni.fr.shoppingManager.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ecole.eni.fr.shoppingManager.beans.ArticleRef;
import ecole.eni.fr.shoppingManager.beans.ListeDeCourse;
import ecole.eni.fr.shoppingManager.provider.ArticleRefHelper;
import ecole.eni.fr.shoppingManager.provider.ListeDeCourseHelper;


public class ListeDeCoursesDAL {

    private Context context;

    public ListeDeCoursesDAL(Context context) {
        this.context = context;
    }

    public void addListe(ListeDeCourse liste) {
        context.getContentResolver().insert( ListeDeCourseHelper.CONTENT_URI  , constructListeDeCourseDB(liste));
    }

    private ContentValues constructListeDeCourseDB(ListeDeCourse liste) {
        ContentValues values = new ContentValues();
        values.put("NOMLISTE", liste.getNomListe());
        values.put("PRIXTOTAL", liste.getPrixTotal());
        values.put("DATE", liste.getDateCourses().toString());
        return values;
    }

    /**
     *
     * @return List
     */
    public List<ListeDeCourse> getListeDeCourses() {

        String[] columns = ListeDeCourseHelper.getColumns();

        Cursor cursor = context.getContentResolver().query(ListeDeCourseHelper.CONTENT_URI,
                columns, null,
                null, null);

        List<ListeDeCourse> listeDeCourses = new ArrayList<ListeDeCourse>() ;

        if(cursor != null && cursor.moveToFirst()) {

            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("_ID"));
                String nomListe = cursor.getString(cursor.getColumnIndex("NOMLISTE"));
                Float prixTotal = cursor.getFloat(cursor.getColumnIndex("PRIXTOTAL"));
                Date date = new Date(cursor.getString(cursor.getColumnIndex("DATE")));
                //listeDeCourses.add(new ListeDeCourse(prixTotal, date, nomListe));
            }while (cursor.moveToNext());

            cursor.close();
        }

        return listeDeCourses;
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
      /*  if (cursor != null && cursor.moveToFirst()) {
            article = new ArticleRef(cursor.getInt(cursor.getColumnIndex("_ID")),
                    cursor.getString(cursor.getColumnIndex("NOM")),
                    cursor.getFloat(cursor.getColumnIndex("PRIX")),
                    cursor.getString(cursor.getColumnIndex("DESCRIPTION")),
                    cursor.getString(cursor.getColumnIndex("IMG")));

            cursor.close();
        }*/


        return article;

    }

    public void setArticle(Integer listeId, ListeDeCourse liste) {

        context.getContentResolver().update(ListeDeCourseHelper.CONTENT_URI, constructListeDeCourseDB(liste),
                "_ID=?"
                , new String[]{String.valueOf(listeId)});

    }

}
