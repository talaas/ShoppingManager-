package ecole.eni.fr.shoppingManager.provider;

import android.content.Context;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;


public class ArticleRefHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "ShoppingManager.db";
    public final static int DATABASE_VERSION = 1;
    private final static String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "ARTICLES ("
            + "_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT,"
            + "DESCRIPTION TEXT,"
            + "PRICE REAL,"
            + "RATE REAL,"
            + "URL TEXT,"
            + "BOUGHT INTEGER)";

    private final static String QUERY_DELETE_TABLE = "DROP TABLE IF EXISTS ARTICLES";
    public final static String TABLE_NAME = "ARTICLES";

    public final static String _ID = "_ID";
    public final static String _NAME = "NAME";
    public final static String _DESCRIPTION = "DESCRIPTION";
    public final static String _PRICE = "PRICE";
    public final static String _RATE = "RATE";
    public final static String _URL = "URL";
    public final static String _BOUGHT = "BOUGHT";

    /**
     * Information Provider
     */
    public static final String AUTHORITY = "fr.eni.ecole.article.provider";

    private static final int DIR = 0;
    private static final int ITEM = 1;

    private static final UriMatcher membreMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        membreMatcher.addURI(AUTHORITY, "metier", DIR);
        membreMatcher.addURI(AUTHORITY, "metier/#", ITEM);
    }

    private static final String TYPE_DIR = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + TABLE_NAME;
    private static final String TYPE_ITEM = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + TABLE_NAME;

    // URI
    public static final String STR_URI = "content://" + AUTHORITY + "/" + TABLE_NAME;

    // URI
    public static final Uri CONTENT_URI = Uri.parse(STR_URI);

    public ArticleRefHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL(QUERY_DELETE_TABLE);
            onCreate(db);
        }
    }

    public String getType(Uri uri) {
        // Si aucun paramètre dans l'uri, récupération de plusieurs lignes possible
        if (membreMatcher.match(uri) == 0) {
            return(TYPE_DIR);
        }

        // On ne récupère qu'un élément
        return(TYPE_ITEM);

    }

    /**
     * Renvoi la liste des colonnes sous forme d'un tableau
     * @return
     */
    public static String[] getColumns(){

        String columns[] = new String[] {
                _ID,
                _NAME,
                _DESCRIPTION,
                _RATE,
                _PRICE,
                _URL,
                _BOUGHT};

        return columns;
    }
}
