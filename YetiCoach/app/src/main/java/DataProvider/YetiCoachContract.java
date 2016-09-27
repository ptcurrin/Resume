package dataprovider;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;


public final class YetiCoachContract {

    /**
     * Created by Patrick on 9/23/2016.
     */

    public static interface CommonColumns extends BaseColumns {}

    public static final String AUTHORITY =
            "com.paddysoft.yeticoach";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    public static final UriMatcher URI_MATCHER;
    public static final int USER_LOGINS_LIST = 1;
    public static final int USER_LOGINS_ID = 2;
    public static final int USER_LIST = 3;
    public static final int USER_ID = 4;
    public static final int LEAGUE_LIST = 5;
    public static final int LEAGUE_ID = 6;

    static{
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, "userlogins", USER_LOGINS_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, "userlogins/#", USER_LOGINS_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, "users", USER_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, "users/#", USER_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, "leagues", LEAGUE_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, "leagues/#", LEAGUE_ID);
    }

    public static final class UserLogins implements CommonColumns{

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, "userlogins");

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.paddysoft.yeticoach_userlogins";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.com.paddysoft.yeticoach_userlogins";

        public static final String[] PROJECTION_ALL =
                {_ID, "email", "password"};

        public static final String SORT_ORDER_DEFAULT =
                "email ASC";
    }

    public static final class Users implements CommonColumns{

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, "users");

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.paddysoft.yeticoach_users";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.com.paddysoft.yeticoach_users";

        public static final String[] PROJECTION_ALL =
                { _ID, "userid", "firstname", "lastname", "email",
                "phone", "street", "city", "state", "zip" };

        public static final String SORT_ORDER_DEFAULT =
                "lastname ASC";
    }

    public static final class Leagues implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, "leagues");

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.com.paddysoft.yeticoach_leagues";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.com.paddysoft.yeticoach_leagues";

        public static final String[] PROJECTION_ALL =
                { _ID, "leagueid", "name", "userid", "sportid", "minimumage", "maximumage",
                        "startdate", "enddate" };

        public static final String SORT_ORDER_DEFAULT =
                "sportid ASC";


    }
}
