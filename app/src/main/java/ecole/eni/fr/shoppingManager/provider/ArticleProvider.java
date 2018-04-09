package ecole.eni.fr.shoppingManager.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ArticleProvider extends ContentProvider {

    private ArticleHelper dbHelper;

    public ArticleProvider() {

    }

    @Override
    public boolean onCreate() {
        dbHelper = new ArticleHelper(getContext());
        return (dbHelper != null);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if (id < 0)
                return db.delete(
                        ArticleHelper.TABLE_NAME,
                        selection, selectionArgs);
            else
                return db.delete(
                        ArticleHelper.TABLE_NAME,
                        ArticleHelper._ID + "=" + id, selectionArgs);
        } finally {
            db.close();
        }
    }

    @Override
    public String getType(Uri uri) {
        return dbHelper.getType(uri);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
           long id = db.insertOrThrow(ArticleHelper.TABLE_NAME, null, values);

            if (id == -1) {
                throw new RuntimeException(String.format(
                        "%s : Failed to insert [%s] for unknown reasons.","AndroCado", values, uri));
            } else {
                return ContentUris.withAppendedId(uri, id);
            }
        }
        finally {
           db.close();
        }

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (id < 0) {
            return  db.query(ArticleHelper.TABLE_NAME,
                    projection, selection, selectionArgs, null, null,
                    sortOrder);
        } else {
            return      db.query(ArticleHelper.TABLE_NAME,
                    projection, ArticleHelper._ID + "=" + id, null, null, null,
                    null);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            if (id < 0)
                return db.update( ArticleHelper.TABLE_NAME, values, selection, selectionArgs);
            else
                return db.update( ArticleHelper.TABLE_NAME,
                        values, ArticleHelper._ID + "=" + id, null);
        } finally {
            db.close();
        }
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
