package paddysoft.yeticoach;

import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dataprovider.User;
import dataprovider.YLoader;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    TextView txtVwTestBundle;
    private TextView tvUserId;
    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvEmail;
    private TextView tvPhone;
    private TextView tvStreet;
    private TextView tvCity;
    private TextView tvState;
    private TextView tvZip;

    private String cUserId;
    private String cFirst;
    private String cLast;
    private String cEmail;
    private String cPhone;
    private String cStreet;
    private String cCity;
    private String cState;
    private String cZip;

    private User currentUser = null;
    private static final int USER_LOADER_ID = 1;
    private LoaderManager.LoaderCallbacks<Cursor> yCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yCallbacks = this;

        //tvUserId = (TextView)findViewById(R.id.userId);
        tvFirstName = (TextView)findViewById(R.id.userFirstName);
        tvLastName = (TextView)findViewById(R.id.userLastName);
        //tvEmail = (TextView)findViewById(R.id.userEmail);
        //tvPhone = (TextView)findViewById(R.id.userPhone);
        tvStreet = (TextView)findViewById(R.id.userStreet);
        tvCity = (TextView)findViewById(R.id.userCity);
        tvState = (TextView)findViewById(R.id.userState);
        tvZip = (TextView)findViewById(R.id.userZip);

        Intent incomingIntent = getIntent();
        Bundle bundle = incomingIntent.getBundleExtra("LoginDelivery");
        cEmail = bundle.getString("Email");

        android.app.LoaderManager lm = getLoaderManager();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        YLoader userLoader = new YLoader(getApplicationContext(), cEmail);

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch(loader.getId()){
            case USER_LOADER_ID:
                tvFirstName.setText(cursor.getString(cursor.getColumnIndex("firstName")));
                tvLastName.setText(cursor.getString(cursor.getColumnIndex("lastName")));
                tvStreet.setText(cursor.getString(cursor.getColumnIndex("street")));
                tvCity.setText(cursor.getString(cursor.getColumnIndex("city")));
                tvState.setText(cursor.getString(cursor.getColumnIndex("state")));
                tvZip.setText(cursor.getString(cursor.getColumnIndex("zip")));

                break;
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        switch(loader.getId()){
            case USER_LOADER_ID:
                tvFirstName.setText("");
                tvLastName.setText("");
                tvStreet.setText("");
                tvCity.setText("");
                tvState.setText("");
                tvZip.setText("");
        }

    }
}
