package ecole.eni.fr.shoppingManager.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ecole.eni.fr.shoppingManager.beans.ItemListe;
import ecole.eni.fr.shoppingManager.beans.ListeDeCourse;

/**
 * Created by stalaa2016 on 10/04/2018.
 */

public class DataBaseManager extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "ShoppingManager.db";
    private static final int DATABASE_VERSION = 6;

    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE listeDeCourse ("
                + " idListe integer primary key autoincrement,"
                + " article text not null,"
                + " description text not null,"
                + " image integer not null,"
                + " prix float not null,"
                + " qte integer not null,"
                + " isBuy integer not null"
                + ")";
        db.execSQL(strSql);
        Log.i("DATABASE", "onCreate invoke");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // String strSql = "CREATE TABLE T_scores add column ...";
        String strSql = "DROP TABLE listeDeCourse";
        db.execSQL(strSql);
        this.onCreate(db);
        Log.i("DATABASE", "onUpgrade invoke");
    }

    public void deleteAll() {
        String strSql = "Delete from listeDeCourse where 1=1";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE", "Delete All invoke" + strSql);
    }

    public void insertData(String article, String description, int image, float prix, int qte, int isBuy){

        //name = name.replace("'",",,");
        String strSql = "INSERT INTO listeDeCourse (article, description, image, prix, qte, isBuy) values ('"
                + article + "', '" + description + "',  " +  image + ",  " +  prix + ",  "  + qte + ", " +
                isBuy + ")";

        this.getWritableDatabase().execSQL(strSql);

        Log.i("DATABASE", "insertScore invoke" + strSql);
    }

    public List<ItemListe> readAll() {
        List<ItemListe> listeCourses = new ArrayList<>();

        //1ere tech : ordre SQL

        String strSql = "select idListe, article, description, image, prix, qte, isBuy from listeDeCourse";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ItemListe listeDeCourse = new ItemListe(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getFloat(4),
                    cursor.getInt(5),
                    cursor.getInt(6));
            listeCourses.add(listeDeCourse);
            cursor.moveToNext();

        }
        cursor.close();
        return listeCourses;
    }
}
