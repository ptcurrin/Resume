package DataProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import static DataProvider.YetiCoachContract.*;

/**
 * Created by Patrick on 9/22/2016.
 */

public class YContentProvider extends ContentProvider {

    SQLiteOpenHelper dbHelper = null;

    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        boolean useAuthorityUri = false;
        switch(URI_MATCHER.match(uri)){
            case USER_LOGINS_LIST:
                builder.setTables(DBSchema.TBL_USER_LOGINS);
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = UserLogins.SORT_ORDER_DEFAULT;
                }
                break;
            case USER_LOGINS_ID:
                builder.setTables(DBSchema.TBL_USER_LOGINS);
                builder.appendWhere(UserLogins._ID + " = " +
                uri.getLastPathSegment());
                break;
            case USER_LIST:
                builder.setTables(DBSchema.TBL_USERS);
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = Users.SORT_ORDER_DEFAULT;
                }
                break;
            case USER_ID:
                builder.setTables(DBSchema.TBL_USERS);
                builder.appendWhere(Users._ID + " = " +
                uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unsupported Uri: " + uri);
        }
        Cursor cursor = builder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        if(useAuthorityUri){
            cursor.setNotificationUri(
                    getContext().getContentResolver(),
                    YetiCoachContract.CONTENT_URI
            );
        }else{
            cursor.setNotificationUri(
                    getContext().getContentResolver(), uri
            );
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch(URI_MATCHER.match(uri)){
            case USER_LOGINS_LIST:
                return UserLogins.CONTENT_TYPE;
            case USER_LOGINS_ID:
                return UserLogins.CONTENT_ITEM_TYPE;
            case USER_LIST:
                return  Users.CONTENT_TYPE;
            case USER_ID:
                return  Users.CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if(URI_MATCHER.match(uri) != USER_LOGINS_LIST &&
                URI_MATCHER.match(uri) != USER_LOGINS_ID &&
                URI_MATCHER.match(uri) != USER_LIST &&
                URI_MATCHER.match(uri) != USER_ID){
            throw new IllegalArgumentException("Unsupported URI for insertion: " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(URI_MATCHER.match(uri) == USER_LOGINS_LIST){
            long id = db.insert(
                DBSchema.TBL_USER_LOGINS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        }
        else {
            long id = db.insertWithOnConflict(
                    DBSchema.TBL_USER_LOGINS,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE);
            return getUriForId(id, uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private Uri getUriForId(long id, Uri uri){
        if(id > 0){
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            if(true){
                getContext().getContentResolver().notifyChange(itemUri, null);
            }
            return itemUri;
        }
        throw new SQLException ("Problem while inserting into uri: " + uri);
    }
}
