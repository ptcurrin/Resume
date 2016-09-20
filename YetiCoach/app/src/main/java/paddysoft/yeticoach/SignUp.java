package paddysoft.yeticoach;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Patrick on 9/20/2016.
 */

public class SignUp extends DialogFragment {

    private TextView etFirst;
    private TextView etLast;
    private TextView etPhone;
    private TextView etEmail;
    private TextView etPassword;
    private Button btnConfirm;

    public interface onSignUpConfirmedListener{
        public void signedUpPressed(String first, String last, String phone,
                                    String email, String password);
    }

    onSignUpConfirmedListener signUpConfirmed;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            signUpConfirmed = (onSignUpConfirmedListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onSignUpConfirmed");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_dialog_sign_up, container, false);

        btnConfirm = (Button)v.findViewById(R.id.btnSignUpConfirm);
        etFirst = (TextView)v.findViewById(R.id.etSignInFirstName);
        etLast =  (TextView)v.findViewById(R.id.etSignInLastName);
        etPhone = (TextView)v.findViewById(R.id.etSignInPhone);
        etEmail = (TextView)v.findViewById(R.id.etSignUpEmail);
        etPassword = (TextView)v.findViewById(R.id.passwordSignUp);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //(if certain regex and database rules are properly observed then...
                boolean goodData = true;

                if(goodData) {
                    signUpConfirmed.signedUpPressed(
                            etFirst.getText().toString(),
                            etLast.getText().toString(),
                            etPhone.getText().toString(),
                            etEmail.getText().toString(),
                            etPassword.getText().toString()
                    );
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Certain information is not going to work here...",
                            Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        return v;
    }

}
