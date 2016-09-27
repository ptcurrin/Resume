package dataprovider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.net.URI;
import java.util.ArrayList;

import static dataprovider.YetiCoachContract.URI_MATCHER;
import static dataprovider.YetiCoachContract.USER_ID;
import static dataprovider.YetiCoachContract.USER_LIST;
import static dataprovider.YetiCoachContract.USER_LOGINS_ID;
import static dataprovider.YetiCoachContract.USER_LOGINS_LIST;
import static dataprovider.YetiCoachContract.LEAGUE_LIST;
import static dataprovider.YetiCoachContract.LEAGUE_ID;

public class YetiContentProvider extends ContentProvider {

    public YetiContentProvider() {
    }

    public static final String AUTHORITY =
            "com.paddysoft.yeticoach";

    SQLiteOpenHelper dbHelper = null;
    private final ThreadLocal<Boolean> mIsInBatchMode = new ThreadLocal<Boolean>();

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
                    sortOrder = YetiCoachContract.UserLogins.SORT_ORDER_DEFAULT;
                }
                break;
            case USER_LOGINS_ID:
                builder.setTables(DBSchema.TBL_USER_LOGINS);
                builder.appendWhere(YetiCoachContract.UserLogins._ID + " = " +
                        uri.getLastPathSegment());
                break;
            case USER_LIST:
                builder.setTables(DBSchema.TBL_USERS);
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = YetiCoachContract.Users.SORT_ORDER_DEFAULT;
                }
                break;
            case USER_ID:
                builder.setTables(DBSchema.TBL_USERS);
                builder.appendWhere(YetiCoachContract.Users._ID + " = " +
                        uri.getLastPathSegment());
                break;
            case LEAGUE_LIST:
                builder.setTables(DBSchema.TBL_LEAGUES);
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder = YetiCoachContract.Leagues.SORT_ORDER_DEFAULT;
                break;
            case LEAGUE_ID:
                builder.setTables(DBSchema.TBL_LEAGUES);
                builder.appendWhere(YetiCoachContract.Leagues._ID + " = " +
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
                return YetiCoachContract.UserLogins.CONTENT_TYPE;
            case USER_LOGINS_ID:
                return YetiCoachContract.UserLogins.CONTENT_ITEM_TYPE;
            case USER_LIST:
                return  YetiCoachContract.Users.CONTENT_TYPE;
            case USER_ID:
                return  YetiCoachContract.Users.CONTENT_ITEM_TYPE;
            case LEAGUE_LIST:
                return  YetiCoachContract.Leagues.CONTENT_TYPE;
            case LEAGUE_ID:
                return  YetiCoachContract.Leagues.CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        if (URI_MATCHER.match(uri) != USER_LOGINS_LIST &&
                URI_MATCHER.match(uri) != USER_LOGINS_ID &&
                URI_MATCHER.match(uri) != USER_LIST &&
                URI_MATCHER.match(uri) != USER_ID &&
                URI_MATCHER.match(uri) != LEAGUE_LIST &&
                URI_MATCHER.match(uri) != LEAGUE_ID) {
            throw new IllegalArgumentException("Unsupported URI for insertion: " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (URI_MATCHER.match(uri) == USER_LOGINS_LIST) {
            long id = db.insert(
                    DBSchema.TBL_USER_LOGINS,
                    null,
                    values
            );
            return getUriForId(id, uri);
        } else if (URI_MATCHER.match(uri) == USER_LIST) {
            long id = db.insert(
                    DBSchema.TBL_USERS,
                    null,
                    values
            );
        } else if (URI_MATCHER.match(uri) == LEAGUE_LIST) {
            long id = db.insert(
                    DBSchema.TBL_LEAGUES,
                    null,
                    values
            );
        }
        // TODO: Investigate insertWithOnConflict
        else {
            long id = db.insertWithOnConflict(
                    DBSchema.TBL_USER_LOGINS,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE);
            return getUriForId(id, uri);
        }
        // TODO: insert matchers for the rest of my objects. Shoot me
        return null;
    }


    // TODO: make delete and update actions for each object
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
            if(!mIsInBatchMode()){
                getContext().getContentResolver().notifyChange(itemUri, null);
            }
            return itemUri;
        }
        throw new SQLException("Problem while inserting into uri: " + uri);
    }

    private boolean mIsInBatchMode() {
        return mIsInBatchMode.get() != null && mIsInBatchMode.get();
    }

    @Override
    public ContentProviderResult[] applyBatch(
            ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        mIsInBatchMode.set(true);
        // the next line works because SQLiteDatabase
        // uses a thread local SQLiteSession object for
        // all manipulations
        db.beginTransaction();
        try {
            final ContentProviderResult[] retResult = super.applyBatch(operations);
            db.setTransactionSuccessful();
            getContext().getContentResolver().notifyChange(YetiCoachContract.CONTENT_URI, null);
            return retResult;
        }
        finally {
            mIsInBatchMode.remove();
            db.endTransaction();
        }
    }


}
