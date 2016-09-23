package DataProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "little_yeti.db";

    // UserLogins Table Definition
    public final static String USER_LOGINS_TN  = "userlogins";
    public final static String USER_LOGINS_CN_ID = "_id";
    public final static String USER_LOGINS_CN_EMAIL = "email";
    public final static String USER_LOGINS_CN_PASSWORD = "password";

    // Users Table Definition
    public final static String USERS_TN = "users";
    public final static String USERS_COL_ID = "_id";
    public final static String USERS_COL_USER_ID = "userId";
    public final static String USERS_COL_FIRSTNAME = "firstname";
    public final static String USERS_COL_LASTNAME = "lastname";
    public final static String USERS_COL_EMAIL = "email";
    public final static String USERS_COL_PHONE = "phone";
    public final static String USERS_COL_STREET = "street";
    public final static String USERS_COL_CITY = "city";
    public final static String USERS_COL_STATE = "state";
    public final static String USERS_COL_ZIP = "zip";

    // Enrollments Table Definition
    public final static String ENROLLMENTS_TN = "enrollments";
    public final static String ENROLLMENTS_COL_ID = "_id";
    public final static String ENROLLMENTS_COL_TRANS_ID = "transId";
    public final static String ENROLLMENTS_COL_PARENT_ID = "parentId";
    public final static String ENROLLMENTS_COL_TEAM_ID = "teamId";

    // Leagues Table Definition
    public final static String LEAGUES_TN = "leagues";
    public final static String LEAGUES_COL_ID = "_id";
    public final static String LEAGUES_COL_LEAGUE_ID = "leagueId";
    public final static String LEAGUES_COL_NAME = "name";
    public final static String LEAGUES_COL_USER_ID = "userId";
    public final static String LEAGUES_COL_SPORT_ID = "sportId";
    public final static String LEAGUES_COL_MIN_AGE = "minimumAge";
    public final static String LEAGUES_COL_MAX_AGE = "maximumAge";
    public final static String LEAGUES_COL_START_DATE = "startDate";
    public final static String LEAGUES_COL_END_DATE = "endDate";

    // Parents Table Definition
    public final static String PARENTS_TN = "parents";
    public final static String PARENTS_COL_ID = "_id";
    public final static String PARENTS_COL_PARENT_ID = "parentId";
    public final static String PARENTS_COL_PLAYER_ID = "playerId";
    public final static String PARENTS_COL_USER_ID = "userId";

    // Players Table Definition
    public final static String PLAYERS_TN = "players";
    public final static String PLAYERS_COL_ID = "_id";
    public final static String PLAYERS_COL_PLAYER_ID = "playerId";
    public final static String PLAYERS_COL_FIRST_NAME = "firstName";
    public final static String PLAYERS_COL_LAST_NAME = "lastName";
    public final static String PLAYERS_COL_DATE_OF_BIRTH = "dateOfBirth";

    // Sports Table Definition
    public final static String SPORTS_TN = "sports";
    public final static String SPORTS_COL_ID = "_id";
    public final static String SPORTS_COL_SPORT_ID = "sportId";
    public final static String SPORTS_COL_NAME = "name";
    public final static String SPORTS_COL_DESCRIPTION = "description";
    public final static String SPORTS_COL_IMG_PTH = "imgPth";

    // Teams Table Definition
    public final static String TEAMS_TN = "teams";
    public final static String TEAMS_COL_ID = "_id";
    public final static String TEAMS_COL_TEAM_ID = "teamId";
    public final static String TEAMS_COL_NAME = "name";
    public final static String TEAMS_COL_USER_ID = "userId";
    public final static String TEAMS_COL_LEAGUE_ID = "leagueId";

    public final static String TRANSACTIONS_TN = "transactions";
    public final static String TRANSACTIONS_COL_ID = "_id";
    public final static String TRANSACTIONS_COL_TRANS_ID = "transId";
    public final static String TRANSACTIONS_COL_PURPOSE = "purpose";
    public final static String TRANSACTIONS_COL_AMOUNT = "amount";


    //DATES WILL BE INTEGERS USING UNIX TIME IN SECONDS SINCE JAN 1st 1970 00:00:00...
    private static final String SQL_CREATE_USER_LOGINS_TABLE = "" +

            "CREATE TABLE " + USER_LOGINS_TN + " (" +
            USER_LOGINS_CN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USER_LOGINS_CN_EMAIL + " TEXT," +
            USER_LOGINS_CN_PASSWORD + " TEXT);" ;

    private static final String SQL_CREATE_USERS_TABLE = "" +

            "CREATE TABLE " + USERS_TN + " (" +
            USERS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            USERS_COL_USER_ID + " TEXT, " +
            USERS_COL_FIRSTNAME+ " TEXT, " +
            USERS_COL_LASTNAME + " TEXT, " +
            USERS_COL_EMAIL + " TEXT, " +
            USERS_COL_PHONE + " TEXT, " +
            USERS_COL_STREET + " TEXT, " +
            USERS_COL_CITY + " TEXT, " +
            USERS_COL_STATE + " TEXT, " +
            USERS_COL_ZIP + " TEXT);" ;

    private static final String SQL_CREATE_ENROLLMENTS_TABLE = "" +
            "CREATE TABLE " + ENROLLMENTS_TN + " (" +
            ENROLLMENTS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ENROLLMENTS_COL_TRANS_ID + " TEXT, " +
            ENROLLMENTS_COL_PARENT_ID + " TEXT, " +
            ENROLLMENTS_COL_TEAM_ID + " TEXT);" ;

    private static final String SQL_CREATE_LEAGUES_TABLE = "" +

            "CREATE TABLE " + LEAGUES_TN + "(" +
            LEAGUES_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            LEAGUES_COL_LEAGUE_ID + " TEXT, " +
            LEAGUES_COL_NAME + " TEXT, " +
            LEAGUES_COL_USER_ID + " TEXT, " +
            LEAGUES_COL_SPORT_ID + " TEXT, " +
            LEAGUES_COL_MIN_AGE + " INTEGER, " +
            LEAGUES_COL_MAX_AGE + " INTEGER, " +
            LEAGUES_COL_START_DATE + " INTEGER, " +
            LEAGUES_COL_END_DATE + " INTEGER);" ;

    private static final String SQL_CREATE_PARENTS_TABLE = "" +

            "CREATE TABLE " + PARENTS_TN + " (" +
            PARENTS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PARENTS_COL_PARENT_ID + " TEXT, " +
            PARENTS_COL_PLAYER_ID + " TEXT, " +
            PARENTS_COL_USER_ID + " TEXT);" ;

    private static final String SQL_CREATE_PLAYERS_TABLE = "" +

            "CREATE TABLE " + PLAYERS_TN + " (" +
            PLAYERS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PLAYERS_COL_PLAYER_ID + " TEXT, " +
            PLAYERS_COL_FIRST_NAME + " TEXT, " +
            PLAYERS_COL_LAST_NAME + " TEXT, " +
            PLAYERS_COL_DATE_OF_BIRTH + " INTEGER);" ;

    private static final String SQL_CREATE_SPORTS_TABLE = "" +

            "CREATE TABLE " + SPORTS_TN + " (" +
            SPORTS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SPORTS_COL_SPORT_ID + " TEXT, " +
            SPORTS_COL_NAME + " TEXT, " +
            SPORTS_COL_DESCRIPTION + " TEXT, " +
            SPORTS_COL_IMG_PTH + " TEXT);" ;

    private static final String SQL_CREATE_TEAMS_TABLE = "" +

            "CREATE TABLE " + TEAMS_TN + "(" +
            TEAMS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEAMS_COL_TEAM_ID + " TEXT, " +
            TEAMS_COL_NAME + " TEXT, " +
            TEAMS_COL_USER_ID + " TEXT, " +
            TEAMS_COL_LEAGUE_ID + " TEXT);" ;

    private static final String SQL_CREATE_TRANSACTIONS_TABLE = "" +

            "CREATE TABLE " + TRANSACTIONS_TN + " (" +
            TRANSACTIONS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TRANSACTIONS_COL_TRANS_ID + " TEXT, " +
            TRANSACTIONS_COL_PURPOSE + " TEXT, " +
            TRANSACTIONS_COL_AMOUNT + " REAL);";

    private static final String[] SQL_DELETE_ENTRIES = {
            "DROP TABLE IF EXISTS " + USER_LOGINS_TN + ";",
            "DROP TABLE IF EXISTS  " + USERS_TN + ";" ,
            "DROP TABLE IF EXISTS  " + ENROLLMENTS_TN + ";" ,
            "DROP TABLE IF EXISTS  " + LEAGUES_TN + ";" ,
            "DROP TABLE IF EXISTS  " + PARENTS_TN + ";" ,
            "DROP TABLE IF EXISTS  " + PLAYERS_TN + ";" ,
            "DROP TABLE IF EXISTS  " + SPORTS_TN + ";" ,
            "DROP TABLE IF EXISTS  " + TEAMS_TN + ";" ,
            "DROP TABLE IF EXISTS  " + TRANSACTIONS_TN + ";" };

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENROLLMENTS_TABLE);
        db.execSQL(SQL_CREATE_LEAGUES_TABLE);
        db.execSQL(SQL_CREATE_PARENTS_TABLE);
        db.execSQL(SQL_CREATE_PLAYERS_TABLE);
        db.execSQL(SQL_CREATE_SPORTS_TABLE);
        db.execSQL(SQL_CREATE_TEAMS_TABLE);
        db.execSQL(SQL_CREATE_TRANSACTIONS_TABLE);
        db.execSQL(SQL_CREATE_USER_LOGINS_TABLE);
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL("INSERT INTO users VALUES(1, 'QWERTYUIOP', 'PATRICK', 'CURRIN', 'CURRIN.PATRICK@YAHOO.COM', '9198127701', '316 MAIN STREET', 'OXFORD', 'NC', '27565')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String s:SQL_DELETE_ENTRIES) {
            db.execSQL(s);
        }
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
