package DataProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;


/**
 * Created by Patrick on 9/22/2016.
 */

public class YContentProvider extends ContentProvider {

    public static final String AUTHORITY =
            "com.paddysoft.yeticoach";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    SQLiteDatabase db;

    @Override
    public boolean onCreate() {

        db = new DBHelper(getContext()).getWritableDatabase();
        return (db == null)?false:true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String orderBy = "";

        if(TextUtils.isEmpty(sortOrder)){
            orderBy = null;
        }else{
            orderBy = sortOrder;
        }

        Cursor cursor = null;
        
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
