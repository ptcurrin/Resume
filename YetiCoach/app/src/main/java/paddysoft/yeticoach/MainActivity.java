package paddysoft.yeticoach;

import android.content.ContentValues;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import DataProvider.DBHelper;
import DataProvider.YContentProvider;

public class MainActivity extends AppCompatActivity
        implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

    TextView txtVwTestBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent incomingIntent = getIntent();
        Bundle bundle = incomingIntent.getBundleExtra("LoginDelivery");

        txtVwTestBundle = (TextView)findViewById(R.id.MainActivityBundleTest);
        txtVwTestBundle.setText(bundle.getString("Email"));

        //getLoaderManager().initLoader(0, null, this);
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(DBHelper.SPORTS_COL_SPORT_ID, "sport1");
//        values.put(DBHelper.SPORTS_COL_NAME, "Hockey");
//        values.put(DBHelper.SPORTS_COL_DESCRIPTION, "Violent");
//        values.put(DBHelper.SPORTS_COL_IMG_PTH, "Hell");
//        if(db.isOpen()) {
//            db.insert(DBHelper.SPORTS_TN, null, values);
//        }
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.SPORTS_TN, null);
//
//        int count = 0;
//
//        if(cursor != null){
//            cursor.moveToNext();
//            count = cursor.getCount();
//        }
//
//        txtVwTestBundle.setText("" + count);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {



        return null;
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }
}
