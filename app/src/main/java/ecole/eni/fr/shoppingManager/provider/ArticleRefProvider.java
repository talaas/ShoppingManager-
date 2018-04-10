package ecole.eni.fr.shoppingManager.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ArticleRefProvider extends ContentProvider {

    private ArticleRefHelper dbHelper;

    public ArticleRefProvider() {

    }

    @Override
    public boolean onCreate() {
        dbHelper = new ArticleRefHelper(getContext());
        return (dbHelper != null);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        long id = getId(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            if (id < 0)
                return db.delete(
                        ArticleRefHelper.TABLE_NAME,
                        selection, selectionArgs);
            else
                return db.delete(
                        ArticleRefHelper.TABLE_NAME,
                        ArticleRefHelper._ID + "=" + id, selectionArgs);
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
           long id = db.insertOrThrow(ArticleRefHelper.TABLE_NAME, null, values);

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
            return  db.query(ArticleRefHelper.TABLE_NAME,
                    projection, selection, selectionArgs, null, null,
                    sortOrder);
        } else {
            return      db.query(ArticleRefHelper.TABLE_NAME,
                    projection, ArticleRefHelper._ID + "=" + id, null, null, null,
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
                return db.update( ArticleRefHelper.TABLE_NAME, values, selection, selectionArgs);
            else
                return db.update( ArticleRefHelper.TABLE_NAME,
                        values, ArticleRefHelper._ID + "=" + id, null);
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
