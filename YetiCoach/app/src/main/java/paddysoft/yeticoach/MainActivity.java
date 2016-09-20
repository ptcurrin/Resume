package paddysoft.yeticoach;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SignUp.onSignUpConfirmedListener {

    public Button btnSignUpMain;
    private SignUp dialogSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUpMain = (Button)findViewById(R.id.btnSignUp);


        btnSignUpMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSignUp = new SignUp();
                FragmentManager fragmentManager = getFragmentManager();

                dialogSignUp.show(fragmentManager, "ShowingSignUpTag");
            }
        });


    }

    @Override
    public void signedUpPressed(String first, String last, String phone, String email, String password) {

        Log.v("Test", "Attempted to close SignUp Fragment");

        dialogSignUp.dismiss();
    }
}
