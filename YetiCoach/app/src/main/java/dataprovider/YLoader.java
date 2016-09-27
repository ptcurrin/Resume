package dataprovider;

import android.content.Context;
import android.content.Loader;
import android.content.AsyncTaskLoader;
import android.database.Cursor;


/**
 * Created by Patrick on 9/26/2016.
 */

public class YLoader extends AsyncTaskLoader {

    String email = "";

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     * @param cEmail
     */
    public YLoader(Context context, String cEmail) {
        super(context);

        this.email = cEmail;


    }

    @Override
    public Object loadInBackground() {

        Cursor data = getContext().getContentResolver().query(YetiCoachContract.Users.CONTENT_URI,
            YetiCoachContract.Users.PROJECTION_ALL,
            "email=?",
            new String[] { email },
            null);

        return data;
    }
}
